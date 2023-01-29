package com.ddudu.todo.repository;

import com.ddudu.todo.model.Hashtag;
import com.ddudu.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Test
    public void task_하나_삽입() {

        Optional<Hashtag> hashtag = hashtagRepository.findById(1L);

        if (hashtag.isEmpty()) {
            Hashtag hashtag_new = Hashtag.builder()
                    .hashtag_id(1L)
                    .contents("default")
                    .build();
            hashtagRepository.save(hashtag_new);
        }

        Todo todo = Todo.builder()
                .user_id(2L)
                .public_type(false)
                .undone(false)
                .contents("나들이!!!")
                .hashtag_id(1L)
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
                    .undone(false)
                    .contents("할일 "+i)
                    .build();

            System.out.println("todo: " + todo);

            todoRepository.save(todo);
        }
    }

    @Test
    public void 데이터_수정() {
        Todo todo = Todo.builder()
                .todo_id(15L)
                .user_id(17L)
                .public_type(false)
                .undone(false)
                .contents("할일1_수정본")
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
