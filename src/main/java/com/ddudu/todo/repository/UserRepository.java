package com.ddudu.todo.repository;

import com.ddudu.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // JPA findBy 규칙
    @Query("select u from User u where u.email = :email")
    public Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.kakao_id = :kakao_id")
    public Optional<User> findByKakao_id(@Param("kakao_id") Long kakao_id);

}
