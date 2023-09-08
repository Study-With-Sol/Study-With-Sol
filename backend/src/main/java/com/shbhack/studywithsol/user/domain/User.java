package com.shbhack.studywithsol.user.domain;

import com.shbhack.studywithsol.utils.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    /*
    user_id : pk
    id : 아이디
    password : 비밀번호
    is_parent : 부모여부
    name : 이름
    phone_number : 전화번호
    in_account : 1원 입금 인증 계좌
    transfer_memo : 1원 입금 통장 메모
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "in_account", nullable = false)
    private String inAccount;

    @Column(name = "transfer_memo")
    private String transferMemo;

//    @Column(nullable = false)
    private String id;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String name;


    private String phoneNumber;

    @Column(name = "is_parent", nullable = false)
    private Boolean isParent;

    public User(String id, String password, String name, String phoneNumber, Boolean isParent){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isParent = isParent;
        this.createdDate = LocalDateTime.now().plusHours(9);
    }

}