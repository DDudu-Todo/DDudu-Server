package com.ddudu.todo.service;

import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.model.HashTag;
import com.ddudu.todo.model.Todo;
import com.ddudu.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

  private final TodoRepository todoRepository;

  @Override
  public List<GetTodoDTO> getList(Long user_id) {
    List<Todo> list = todoRepository.getList(user_id);

    List<GetTodoDTO> result = list.stream().map(todo -> {
      return entityToGetDto(todo);
    }).collect(Collectors.toList());

    // TODO: 반환하기 전에 정렬 필요

    return result;
  }

  @Override
  public Long add(Long user_id, String task) {
    Todo todo = Todo.builder()
            .user_id(user_id)
            .public_type(false)
            .is_challenge(false)
            .due_date(new Timestamp(System.currentTimeMillis()))
            .done(false)
            .contents(task)
            .hash_tag(HashTag.기타)
            .build();

    todoRepository.save(todo);
    return todo.getId();
  }

  @Override
  public Long remove(Long task_id) {

    todoRepository.deleteById(task_id);

    return task_id;
  }
}
