package com.ddudu.todo.controller;

import com.ddudu.todo.dto.DeleteTodoDTO;
import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.dto.SetTodoDTO;
import com.ddudu.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @GetMapping("/{user_id}")
  public ResponseEntity<List<GetTodoDTO>> getList(@PathVariable("user_id") Long user_id) {

    List<GetTodoDTO> list = todoService.getList(user_id);
    System.out.println(list);

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<List<GetTodoDTO>> add(@RequestBody SetTodoDTO req) {

    log.info("넘어온 데이터: " + req);

    Long user_id = req.getUser_id();
    String contents = req.getContents();

    todoService.add(user_id, contents);

    List<GetTodoDTO> list = todoService.getList(user_id);

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @DeleteMapping("/remove")
  public ResponseEntity<List<GetTodoDTO>> remove(@RequestBody DeleteTodoDTO req) {

    log.info("넘어온 데이터: " + req);

    long task_id = req.getTask_id();
    long user_id = req.getUser_id();

    long removed_id = todoService.remove(task_id);

    if (removed_id == task_id) {
      List<GetTodoDTO> list = todoService.getList(user_id);

      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
