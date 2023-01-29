package com.ddudu.todo.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todo_id;

    private Long user_id;

    @Column(nullable = false)
    private boolean public_type;

    @Column(nullable = false)
    private boolean undone;

    @Column(nullable = false)
    private String contents;

    private Long hashtag_id;

    private Timestamp deleted_at;

    private String date;

    // content 수정하는 메서드
    public void changeContent(String contents) {
        this.contents = contents;
    }

    // 삭제 시간 기록하는 메서드
    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    // done 수정하는 메서드
    public void checkTodo() {
        undone = !this.undone;
    }
}
