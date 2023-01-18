package com.ddudu.todo.repository;

import com.ddudu.todo.model.HashTag;
import com.ddudu.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void task_하나_삽입() {
        Todo todo = Todo.builder()
                .user_id(51L)
                .public_type(false)
                .is_challenge(false)
                .due_date(new Timestamp(System.currentTimeMillis()))
                .done(false)
                .contents("나들이!!!")
                .hash_tag(HashTag.나들이)
                .build();

        System.out.println(todo.toString());

        todoRepository.save(todo);
    }

    @Test
    public void 데이터_삽입() {
        for (int i = 0; i < 200; i++) {
            Long user_id = (long) (Math.random() * 50 + 1);

            Todo todo = Todo.builder()
                    .user_id(user_id)
                    .public_type(false)
                    .is_challenge(false)
                    .due_date(new Timestamp(System.currentTimeMillis()))
                    .done(false)
                    .contents("할일 "+i)
                    .hash_tag(HashTag.운동)
                    .build();

            System.out.println("todo: " + todo);

            todoRepository.save(todo);
        }
    }

    @Test
    public void 데이터_수정() {
        Todo todo = Todo.builder()
                .id(15L)
                .user_id(17L)
                .public_type(false)
                .is_challenge(false)
                .due_date(new Timestamp(System.currentTimeMillis()))
                .done(false)
                .contents("할일1_수정본")
                .hash_tag(HashTag.취미)
                .build();

        todoRepository.save(todo);
    }

    @Test
    public void 리스트_가져오기() {
        List<Todo> list = todoRepository.getList(3L);
        for (Todo t : list) {
            System.out.println(t);
        }
    }
}
