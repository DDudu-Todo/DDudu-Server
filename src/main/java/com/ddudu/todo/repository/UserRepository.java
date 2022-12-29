package com.ddudu.todo.repository;

import com.ddudu.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // JPA findBy 규칙
    // select * from user where email = ?
    public User findByEmail(String email);

    // select * from user where kakao_id = ?
//    public User findByKakao_id(String kakao_id);

}
