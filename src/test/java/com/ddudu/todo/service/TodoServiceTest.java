package com.ddudu.todo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {

  @Autowired
  private TodoService todoService;

  @Test
  public void task_데이터_수정() {
    Long result = todoService.modify(221L, "hihi 할일 수정");
    if (result != null) {
      System.out.println(result + "번 수정 완료");
    }
    else {
      System.out.println("수정 실패");
    }
  }
}
