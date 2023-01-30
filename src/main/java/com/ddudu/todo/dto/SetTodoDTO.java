package com.ddudu.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetTodoDTO {

  private Long user_id;
  private String contents;
  private boolean public_type;
  private String hashtag;

  private String date;
}
