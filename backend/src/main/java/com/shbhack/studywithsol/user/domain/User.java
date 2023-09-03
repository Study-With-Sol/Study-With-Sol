package com.shbhack.studywithsol.user.domain;

import com.shbhack.studywithsol.utils.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    /*
    id : pk
    child_id : 자녀 pk
    parent_id : 부모 pk
    is_parent : 부모여부
    nickname : 아이디
    password : 비밀번호
    name : 이름
    email : 이메일
    phone_number : 전화번호
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private User child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private User parent;

    @Column(name = "is_parent", nullable = false)
    private Boolean isParent;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    public User(String nickname,String name, String email, String phoneNumber){
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
