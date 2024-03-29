package com.ddudu.todo.controller;

import com.ddudu.todo.dto.DeleteTodoDTO;
import com.ddudu.todo.dto.GetTodoDTO;
import com.ddudu.todo.dto.SetTodoDTO;
import com.ddudu.todo.dto.UpdateTodoDTO;
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

  @GetMapping("/{date}/{user_id}")
  public ResponseEntity<List<GetTodoDTO>> getList(@PathVariable("date") String date,
                                                  @PathVariable("user_id") Long user_id) {

    List<GetTodoDTO> list = todoService.getList(user_id, date);
    log.info("조회된 데이터 목록: " + list);

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<List<GetTodoDTO>> add(@RequestBody SetTodoDTO req) {

    log.info("넘어온 데이터: " + req);

    Long user_id = todoService.add(req);
    if (user_id == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    List<GetTodoDTO> list = todoService.getList(user_id, req.getDate());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }


  @DeleteMapping("/remove")
  public ResponseEntity<List<GetTodoDTO>> remove(@RequestBody DeleteTodoDTO req) {

    log.info("넘어온 데이터: " + req);

    long removed_id = todoService.remove(req);

    if (removed_id == req.getTodo_id()) {
      List<GetTodoDTO> list = todoService.getList(req.getUser_id(), req.getDate());

      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/modify")
  public ResponseEntity<List<GetTodoDTO>> modify(@RequestBody UpdateTodoDTO req) {

    log.info("넘어온 데이터: " + req);

    long modified_id = todoService.modify(req);

    if (modified_id == req.getTodo_id()) {
      List<GetTodoDTO> list = todoService.getList(req.getUser_id(), req.getDate());

      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/change-status")
  public ResponseEntity<Long> changeStatus(@RequestBody DeleteTodoDTO req) {
    log.info("넘어온 데이터(done): " + req);

    long done_id = todoService.changeChecked(req);

    if (done_id == req.getTodo_id()) {
      return new ResponseEntity<>(done_id, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
