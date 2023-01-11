package com.ddudu.todo.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private boolean public_type;

    @Column(nullable = false)
    private boolean is_challenge;

    @Column(nullable = false)
    private Timestamp due_date;

    @Column(nullable = false)
    private boolean done;

    @Column(nullable = false)
    private String contents;

    @Column(columnDefinition = "ENUM('운동','취미','공부','나들이','여행','자기개발')")
    @Enumerated(EnumType.STRING)
    private HashTag hash_tag;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;
    private Timestamp deleted_at;
}
