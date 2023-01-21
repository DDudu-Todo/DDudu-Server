package com.ddudu.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTodoDTO {

  private Long id;
  private boolean public_type;
  private boolean done;
  private String contents;
  private String hashtag;
  private Timestamp created_at;
  private Timestamp updated_at;
  private Timestamp deleted_at;
}
