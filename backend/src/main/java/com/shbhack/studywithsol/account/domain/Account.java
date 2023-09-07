package com.shbhack.studywithsol.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shbhack.studywithsol.account.domain.enums.AccountType;
import com.shbhack.studywithsol.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    /**
     *
     * 예적금/외화/펀드/신탁
     *
     *  id : "PK"
     *  user : "앱 유저"
     *  owner : "계좌 주인명"
     *  type : "예적금",
     *  accountNumber : "계좌번호",
     *  accountName : "계좌이름",
     *  productName : "상품명",
     *  balance : "잔액(통화별)",
     *  createdDate : "신규일",
     *  expirationDate : "만기일",
     *  interestRate : "금리(수익률)",
     *  isMainAccount : "주계좌여부"
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = true)
    private LocalDateTime expirationDate;

    @Column(nullable = true)
    private Double interestRate;

    @Column(nullable = false)
    private Boolean isMainAccount;

}
