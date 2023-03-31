package com.indo.perpustakaanonline.service;

import com.indo.perpustakaanonline.entity.LendBook;
import com.indo.perpustakaanonline.entity.Member;

import java.sql.Date;
import java.util.List;

public interface LendBookService {
    List<LendBook> getAllLendBook();
    LendBook getLendBookById(String id);
    LendBook saveLendBook(LendBook lendBook);
    void deleteLendBook(String id);
    List<LendBook> getLendBookNotReturned();

    Integer countUnreturnedBooks();

    Member findMemberWithMostLendBooksByDate(Date startDate, Date endDate);

    List<LendBook> findLateLendBooks();

    List<Member> findMembersWhoLendBook();

    List<LendBook> findListLendBookByBookId(String bookId);
}
