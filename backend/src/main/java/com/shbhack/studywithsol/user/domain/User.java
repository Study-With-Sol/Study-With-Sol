package com.shbhack.studywithsol.user.domain;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.utils.domain.BaseEntity;
import lombok.Builder;
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
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;


    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String email;

    @Column(name = "is_parent", nullable = false)
    private Boolean isParent;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account mainAccount;

    @Builder
    public User(String id, String password, String name, String email, Boolean isParent){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isParent = isParent;
        this.createdDate = LocalDateTime.now().plusHours(9);
    }

}