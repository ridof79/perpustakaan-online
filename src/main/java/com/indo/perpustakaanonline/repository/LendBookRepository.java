package com.indo.perpustakaanonline.repository;

import com.indo.perpustakaanonline.entity.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendBookRepository extends JpaRepository<LendBook, String> {
    List<LendBook> findByIsReturnedFalse();
}
