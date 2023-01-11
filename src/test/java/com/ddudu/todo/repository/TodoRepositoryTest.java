package com.ddudu.todo.repository;

import com.ddudu.todo.model.HashTag;
import com.ddudu.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class TodoRepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(TodoRepositoryTest.class);

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void 데이터_삽입() {
        Todo todo = Todo.builder()
                .user_id(7L)
                .public_type(false)
                .is_challenge(false)
                .due_date(new Timestamp(System.currentTimeMillis()))
                .done(false)
                .contents("할일1")
                .hash_tag(HashTag.운동)
                .build();

        LOGGER.info(todoRepository.save(todo).toString());
    }

    @Test
    public void 데이터_수정() {
        Todo todo = Todo.builder()
                .id(15L)
                .user_id(7L)
                .public_type(false)
                .is_challenge(false)
                .due_date(new Timestamp(System.currentTimeMillis()))
                .done(false)
                .contents("할일1_수정본")
                .hash_tag(HashTag.취미)
                .build();

        LOGGER.info(todoRepository.save(todo).toString());
    }
}
