package com.ddudu.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {

    @RequestMapping("/calendar") // 기본 페이지 표시
    public String viewCalendar(){
        return "calendar";
    }
}