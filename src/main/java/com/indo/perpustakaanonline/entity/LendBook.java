package com.indo.perpustakaanonline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import java.sql.Date;

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

    @ManyToOne
    private Book book;

    @ManyToOne
    private Member member;

    private Date lendDate;

    private Date returnDate;

    private Boolean isReturned;
}
