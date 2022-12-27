package com.ddudu.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String email;

    @Column()
    private String pw;

    @Column()
    private String kakaoId;

    @Column()
    private String nickName;

    @Column()
    private String initAuthorized;

    @Column()
    private String imageURL;

    @Column()
    private Long continuousChallengesCount;

    @Column()
    private Long successedChallengesCount;

    @Column()
    private Timestamp createdAt;

    @Column()
    private Timestamp updatedAt;

    @Column()
    private Timestamp deletedAt;
}
