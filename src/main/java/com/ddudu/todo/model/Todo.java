package com.ddudu.todo.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Todo extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(columnDefinition = "ENUM('운동','취미','공부','나들이','여행','자기개발','기타')")
    @Enumerated(EnumType.STRING)
    private HashTag hash_tag;

    private Timestamp deleted_at;

    // content 수정하는 메서드
    public void changeContent(String contents) {
        this.contents = contents;
    }
}
