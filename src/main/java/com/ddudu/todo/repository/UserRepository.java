package com.ddudu.todo.repository;

import com.ddudu.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // JPA findBy 규칙
    // select * from user where email = ?
    public Optional<User> findByEmail(String email);

}
