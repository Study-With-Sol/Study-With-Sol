package com.shbhack.studywithsol.transaction.domain;

import com.shbhack.studywithsol.account.domain.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    /**
     *  id : pk
     *  account : 연결계좌
     *  content : 내역
     *  amount : 금액
     *  useDate : 거래일
     *  isDeposit : 입금/출금
     *  recipient : 받는이
     *  sender : 보내는이
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime useDate;

    @Column(nullable = false)
    private Boolean isDeposit;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String sender;

    public Transaction(String content, Long amount, Boolean isDeposit,
                   String recipient, String sender){
        this.content = content;
        this.amount = amount;
        this.useDate = LocalDateTime.now();
        this.isDeposit = isDeposit;
        this.recipient = recipient;
        this.sender = sender;
    }

    public void beTradedIn(Account account){
        this.account = account;
    }
}
