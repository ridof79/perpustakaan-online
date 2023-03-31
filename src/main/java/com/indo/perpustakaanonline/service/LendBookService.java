package com.indo.perpustakaanonline.service;

import com.indo.perpustakaanonline.entity.LendBook;

import java.util.List;

public interface LendBookService {
    List<LendBook> getAllLendBook();
    LendBook getLendBookById(String id);
    LendBook saveLendBook(LendBook lendBook);
    void deleteLendBook(String id);
    List<LendBook> getLendBookNotReturned();
}
