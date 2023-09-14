package com.shbhack.studywithsol.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "connection")
public class Connection {
    /*
    connection_id : pk
    parent_id : 부모키
    children_id : 자녀키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id")
    private Long connectionId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "children_id", nullable = false)
    private User children;

    @Column(name = "is_connection")
    private Boolean isConnection;

    @Builder
    public Connection(User parent, User children){
        this.parent = parent;
        this.children = children;
        this.isConnection = true;
    }
}
