package com.indo.perpustakaanonline.service;

import com.indo.perpustakaanonline.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(String id);
    Book saveBook(Book book);
    void deleteBook(String id);
}
