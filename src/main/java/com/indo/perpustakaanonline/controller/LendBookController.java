package com.indo.perpustakaanonline.controller;

import com.indo.perpustakaanonline.dto.MemberDTO;
import com.indo.perpustakaanonline.entity.LendBook;
import com.indo.perpustakaanonline.entity.Member;
import com.indo.perpustakaanonline.service.LendBookService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/lend-books")
@AllArgsConstructor
public class LendBookController {

    LendBookService lendBookService;

    @GetMapping
    public List<LendBook> getAllLendBook() {
        return lendBookService.getAllLendBook();
    }

    @GetMapping("/{id}")
    public LendBook getLendBookById(@PathVariable String id) {
        return lendBookService.getLendBookById(id);
    }

    @PostMapping
    public LendBook saveLendBook(@RequestBody LendBook lendBook) {
        return lendBookService.saveLendBook(lendBook);
    }

    @DeleteMapping("/{id}")
    public void deleteLendBook(@PathVariable String id) {
        lendBookService.deleteLendBook(id);
    }

    @GetMapping("/not-returned")
    public List<LendBook> getLendBookNotReturned() {
        return lendBookService.getLendBookNotReturned();
    }

    @GetMapping("/unreturned-count")
    public Integer countUnreturnedBooks() {
        return lendBookService.countUnreturnedBooks();
    }

    @GetMapping("/member-most-lend-books")
    public Member findMemberWithMostLendBooksByDate(@RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                    @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return lendBookService.findMemberWithMostLendBooksByDate(startDate, endDate);
    }

    @GetMapping("/late-lend-books")
    public List<LendBook> findLateLendBooks() {
        return lendBookService.findLateLendBooks();
    }

    @GetMapping("/members-with-lend-books")
    public List<MemberDTO> findMembersWhoLendBook() {
        return lendBookService.findMembersWhoLendBook();
    }

    @GetMapping("/book/{bookId}")
    public List<LendBook> findListLendBookByBookId(@PathVariable String bookId) {
        return lendBookService.findListLendBookByBookId(bookId);
    }

}
