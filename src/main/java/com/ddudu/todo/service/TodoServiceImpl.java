package com.ddudu.todo.service;

import com.ddudu.todo.dto.DeleteTodoDTO;
import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.dto.SetTodoDTO;
import com.ddudu.todo.dto.UpdateTodoDTO;
import com.ddudu.todo.model.Hashtag;
import com.ddudu.todo.model.Todo;
import com.ddudu.todo.repository.HashtagRepository;
import com.ddudu.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

  private final TodoRepository todoRepository;

  private final HashtagRepository hashtagRepository;

  @Override
  public List<GetTodoDTO> getList(Long user_id) {
    List<Todo> list = todoRepository.getList(user_id);

    // TODO: 삭제된 데이터 필터링
    // TODO: done(끝난 task) 확인 작업

    List<GetTodoDTO> result = list.stream().map(todo -> {

      GetTodoDTO dto = entityToGetDto(todo);
      Optional<Hashtag> hashtag = hashtagRepository.findById(todo.getHashtag_id());

      // id로 가져온 해시태그를 내용으로 바꿔서 응답 데이터에 삽입되도록
      if (hashtag.isPresent()) {
        dto.setHashtag(hashtag.get().getContents());
        return dto;
      } else {
        return null;
      }

    }).collect(Collectors.toList());

    return result;
  }

  @Override
  public Long add(SetTodoDTO dto) {

    // hashtag의 유무에 따라 생성, 조회 작업
    Long hashtag_id;
    if (hashtagRepository.findByContents(dto.getHashtag()) == null) {
      Hashtag hashtag = hashtagRepository.save(Hashtag.builder()
              .contents(dto.getHashtag())
              .build());

      hashtag_id = hashtag.getHashtag_id();
    } else {
      hashtag_id = hashtagRepository.findByContents(dto.getHashtag());
    }

    Todo todo = Todo.builder()
            .user_id(dto.getUser_id())
            .public_type(dto.isPublic_type())
            .done(false)
            .contents(dto.getContents())
            .hashtag_id(hashtag_id)
            .build();

    todoRepository.save(todo);
    return todo.getUser_id();
  }

  @Override
  public Long remove(DeleteTodoDTO dto) {

    // TODO: 삭제된 시간 기록

    todoRepository.deleteById(dto.getTodo_id());

    return dto.getTodo_id();
  }

  @Override
  public Long modify(UpdateTodoDTO dto) {

    Optional<Todo> todo = todoRepository.findById(dto.getTodo_id());

    if (todo.isPresent()) {
      log.info("before edit: " + todo.get());
      todo.get().changeContent(dto.getContents());
      log.info("after edit: " + todo.get());

      todoRepository.save(todo.get());

      return todo.get().getTodo_id();
    }

    return null;
  }
}
