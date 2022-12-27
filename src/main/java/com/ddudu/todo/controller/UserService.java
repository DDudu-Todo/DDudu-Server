package com.ddudu.todo.controller;

import com.ddudu.todo.advice.RestException;
import com.ddudu.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ddudu.todo.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long userId) {
        User member = userRepository.findById(userId).get();
        System.out.println(member);
        return member;
    }
}