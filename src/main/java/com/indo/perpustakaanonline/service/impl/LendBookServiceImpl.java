package com.indo.perpustakaanonline.service.impl;

import com.indo.perpustakaanonline.entity.LendBook;
import com.indo.perpustakaanonline.repository.LendBookRepository;
import com.indo.perpustakaanonline.service.LendBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
