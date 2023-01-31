package com.ddudu.todo.repository;

import com.ddudu.todo.model.FollowVO;

import java.util.List;

public interface IFollowDAO {
    //팔로우
    void follow(FollowVO follow);

    //언팔로우
    void unfollow(FollowVO follow);

    //팔로우 유무
    int isFollow(FollowVO follow);

}
