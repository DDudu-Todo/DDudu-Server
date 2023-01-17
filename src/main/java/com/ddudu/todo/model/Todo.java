package com.ddudu.todo.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Todo extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

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

    private Timestamp deleted_at;
}
