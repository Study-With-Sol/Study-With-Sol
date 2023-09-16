package com.shbhack.studywithsol.user.domain;

import com.shbhack.studywithsol.utils.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "connection")
public class Connection extends BaseEntity {
    /*
    connection_id : pk
    parent_id : 부모키
    children_id : 자녀키
    alias : 자녀와의 관계
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id")
    private Long connectionId;

    @Column(nullable = false)
    private String alias;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "children_id", nullable = false)
    private User children;

    @Column(name = "is_connect")
    private Boolean isConnect;

    @Builder
    public Connection(User parent, User children, String alisa){
        this.parent = parent;
        this.children = children;
        this.alias = alisa;
        this.isConnect = true;
        this.createdDate = LocalDateTime.now().plusHours(9);
    }

    public void disconnect(){
        this.isConnect =false;
        this.updatedDate = LocalDateTime.now().plusHours(9);
    }
}
