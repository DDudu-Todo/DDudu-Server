package com.ddudu.todo.service;

import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

  public List<GetTodoDTO> getList(Long user_id);

  public Long add(Long user_id, String task);

  public Long remove(Long task_id);

  public Long modify(Long task_id, String task);

  default public Todo getDtoToEntity(GetTodoDTO dto) {
    return Todo.builder()
            .id(dto.getId())
            .user_id(dto.getUser_id())
            .public_type(dto.isPublic_type())
            .is_challenge(dto.is_challenge())
            .due_date(dto.getDue_date())
            .done(dto.isDone())
            .contents(dto.getContents())
            .hash_tag(dto.getHashTag())
            .deleted_at(dto.getDeleted_at())
            .build();
  }

  default public GetTodoDTO entityToGetDto(Todo entity) {
    return GetTodoDTO.builder()
            .id(entity.getId())
            .user_id(entity.getUser_id())
            .public_type(entity.isPublic_type())
            .is_challenge(entity.is_challenge())
            .due_date(entity.getDue_date())
            .done(entity.isDone())
            .contents(entity.getContents())
            .hashTag(entity.getHash_tag())
            .deleted_at(entity.getDeleted_at())
            .created_at(entity.getCreated_at())
            .updated_at(entity.getUpdated_at())
            .build();
  }

}
