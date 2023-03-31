package com.indo.perpustakaanonline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "trx_lend_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LendBook {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "trx_lend_book",
            joinColumns = @JoinColumn(name = "lend_book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @ManyToOne
    private Member member;

    private Date lendDate;

    private Date returnDate;

    private Boolean isReturned;
}
