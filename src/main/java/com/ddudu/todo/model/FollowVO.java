package com.ddudu.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class FollowVO {
    private int followNo;
    private Date regDate;

}
