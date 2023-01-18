package com.ddudu.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTodoDTO {

  private Long user_id;
  private Long task_id;
  private String contents;
}
