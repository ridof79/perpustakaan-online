package com.indo.perpustakaanonline.service.impl;

import com.indo.perpustakaanonline.dto.MemberDTO;
import com.indo.perpustakaanonline.entity.Book;
import com.indo.perpustakaanonline.entity.LendBook;
import com.indo.perpustakaanonline.entity.Member;
import com.indo.perpustakaanonline.repository.LendBookRepository;
import com.indo.perpustakaanonline.service.BookService;
import com.indo.perpustakaanonline.service.LendBookService;
import com.indo.perpustakaanonline.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendBookServiceImpl implements LendBookService {

    LendBookRepository  lendBookRepository;
    BookService bookService;
    MemberService memberService;

    @Override
    public List<LendBook> getAllLendBook() {
        return lendBookRepository.findAll();
    }

    @Override
    public LendBook getLendBookById(String id) {
        return lendBookRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public LendBook saveLendBook(LendBook lendBook) {

        List<Book> books = lendBook.getBooks()
                .stream()
                .map(Book::getId)
                .toList()
                .stream()
                .map(book -> bookService.getBookById(book))
                .toList();

        for (Book book : books) {
            if (book.getStock() == 0) {
                throw new RuntimeException(String.format("Book %s Out of Stock", book.getTitle()));
            } else {
                book.setStock(book.getStock() - 1);
                bookService.saveBook(book);
            }
        }

        LendBook savedLendBook = new LendBook(
                null,
                books,
                memberService.getMemberById(lendBook.getMember().getId()),
                Date.valueOf(LocalDate.now()),
                lendBook.getReturnDate(),
                false
        );
        return lendBookRepository.save(savedLendBook);
    }

    @Override
    public void deleteLendBook(String id) {
        lendBookRepository.deleteById(id);
    }

    @Override
    public List<LendBook> getLendBookNotReturned() {
        return lendBookRepository.findByIsReturnedFalse();
    }

    @Override
    public Integer countUnreturnedBooks() {
        return lendBookRepository.countUnreturnedBooks();
    }

    @Override
    public Member findMemberWithMostLendBooksByDate(Date startDate, Date endDate) {
        List<LendBook> lendBooks = lendBookRepository.findByLendDateBetween(startDate, endDate);

        Map<Member, Integer> lendBookCounts = lendBooks.stream()
                .collect(Collectors.groupingBy(LendBook::getMember, Collectors.summingInt(lendBook -> lendBook.getBooks().size())));

        return lendBookCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public List<LendBook> findLateLendBooks() {
        List<LendBook> lendBooks = lendBookRepository.findAll();
        Date currentDate = new Date(System.currentTimeMillis());

        return lendBooks.stream()
                .filter(lendBook -> lendBook.getIsReturned() != null && !lendBook.getIsReturned() && lendBook.getReturnDate().before(currentDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findMembersWhoLendBook() {
        List<Member> members = new ArrayList<>();
        List<Object[]> rows = lendBookRepository.findMembersWithLendBooks();
        for (Object[] row : rows) {
            Member member = new Member();
            member.setId((String) row[0]);
            member.setName((String) row[1]);
            member.setAddress((String) row[2]);
            members.add(member);
        }

        return members.stream().map(MemberDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<LendBook> findListLendBookByBookId(String bookId) {
        return lendBookRepository.findListLendByBookId(bookId);
    }
}
