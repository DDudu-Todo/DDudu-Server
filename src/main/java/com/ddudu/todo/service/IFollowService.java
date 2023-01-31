package com.ddudu.todo.service;

import com.ddudu.todo.model.FollowVO;

public interface IFollowService {
    //팔로우
    void follow(FollowVO follow);

    //언팔로우
    void unfollow(FollowVO follow);

    //팔로우 유무
    int isFollow(FollowVO follow);
}

