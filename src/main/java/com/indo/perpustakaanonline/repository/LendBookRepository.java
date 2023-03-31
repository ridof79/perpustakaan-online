package com.indo.perpustakaanonline.repository;

import com.indo.perpustakaanonline.entity.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface LendBookRepository extends JpaRepository<LendBook, String> {
    List<LendBook> findByIsReturnedFalse();

    @Query(
            value = "SELECT COUNT(*) FROM trx_lend_book WHERE is_returned = false",
            nativeQuery = true)
    Integer countUnreturnedBooks();

    @Query(
            value = "SELECT * FROM trx_lend_book WHERE lend_date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<LendBook> findByLendDateBetween(Date startDate, Date endDate);

    @Query(
            value = "SELECT DISTINCT m.* FROM mst_member m INNER JOIN trx_lend_book lb ON m.id = lb.member_id",
            nativeQuery = true)
    List<Object[]> findMembersWithLendBooks();

    @Query(
            value = "SELECT lb.* FROM trx_lend_book lb INNER JOIN book_lend_book blb ON lb.id = blb.lend_book_id WHERE blb.book_id = :bookId",
            nativeQuery = true)
    List<LendBook> findListLendByBookId(String bookId);
}
