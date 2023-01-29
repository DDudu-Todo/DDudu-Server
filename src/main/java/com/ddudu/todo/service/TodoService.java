package com.ddudu.todo.service;

import com.ddudu.todo.dto.DeleteTodoDTO;
import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.dto.SetTodoDTO;
import com.ddudu.todo.dto.UpdateTodoDTO;
import com.ddudu.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

  public List<GetTodoDTO> getList(Long user_id, String date);

  public Long add(SetTodoDTO dto);

  public Long remove(DeleteTodoDTO dto);

  public Long modify(UpdateTodoDTO dto);

  public Long changeChecked(DeleteTodoDTO dto);

  default public GetTodoDTO entityToGetDto(Todo entity) {

    return GetTodoDTO.builder()
            .id(entity.getTodo_id())
            .public_type(entity.isPublic_type())
            .undone(entity.isUndone())
            .contents(entity.getContents())
            .created_at(entity.getCreated_at())
            .updated_at(entity.getUpdated_at())
            .deleted_at(entity.getDeleted_at())
            .build();
  }

}
