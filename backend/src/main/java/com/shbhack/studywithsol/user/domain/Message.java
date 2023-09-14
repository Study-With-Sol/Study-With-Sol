package com.shbhack.studywithsol.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    private Long id;

    private String adjective;
    private String noun;
}
