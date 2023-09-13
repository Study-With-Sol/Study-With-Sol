package com.shbhack.studywithsol.account.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.shbhack.studywithsol.account.domain.enums.AccountType;
import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
     *  isMainAccount : "주계좌여부",
     *  isActive : "계좌 상태"
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

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
    private LocalDate createdDate;

    @Column(nullable = true)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private Boolean isMainAccount;

    @Column(nullable = false)
    private Boolean isActive;

    public Account(String owner, AccountType type, String accountNumber, String accountName,
                   String productName, Long balance, LocalDate createdDate, LocalDate expirationDate,
                   Double interestRate, Boolean isActive){
        this.owner = owner;
        this.type = type;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.productName = productName;
        this.balance = balance;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
        this.interestRate = interestRate;
        this.isMainAccount = false;
        this.isActive = isActive;
    }

    public void registeredBy(User user){
        this.user = user;
    }

    public void terminatedBy(){
        this.user = null;
    }

    public void setIsMainAccount(Boolean isMainAccount){
        this.isMainAccount = isMainAccount;
    }

}
