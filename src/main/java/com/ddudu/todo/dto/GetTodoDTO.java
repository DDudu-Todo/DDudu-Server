package com.ddudu.todo.dto;

import com.ddudu.todo.model.HashTag;
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
  private Long user_id;
  private boolean public_type;
  private boolean is_challenge;
  private Timestamp due_date;
  private boolean done;
  private String contents;
  private HashTag hashTag;
  private Timestamp created_at;
  private Timestamp updated_at;
  private Timestamp deleted_at;
}
