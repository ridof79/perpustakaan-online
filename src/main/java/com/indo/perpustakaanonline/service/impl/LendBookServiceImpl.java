package com.indo.perpustakaanonline.service.impl;

import com.indo.perpustakaanonline.entity.LendBook;
import com.indo.perpustakaanonline.entity.Member;
import com.indo.perpustakaanonline.repository.LendBookRepository;
import com.indo.perpustakaanonline.service.LendBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendBookServiceImpl implements LendBookService {

    LendBookRepository  lendBookRepository;

    @Override
    public List<LendBook> getAllLendBook() {
        return lendBookRepository.findAll();
    }

    @Override
    public LendBook getLendBookById(String id) {
        return lendBookRepository.getReferenceById(id);
    }

    @Override
    public LendBook saveLendBook(LendBook lendBook) {
        return lendBookRepository.save(lendBook);
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
    public List<Member> findMembersWhoLendBook() {
        return lendBookRepository.findMembersWithLendBooks();
    }

    @Override
    public List<LendBook> findListLendBookByBookId(String bookId) {
        return lendBookRepository.findListLendByBookId(bookId);
    }
}
