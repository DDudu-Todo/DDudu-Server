package com.ddudu.todo.repository;

import com.ddudu.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

  @Query("select t from Todo t where t.user_id = :user_id")
  List<Todo> getList(@Param("user_id") Long user_id);

  @Query("select t from Todo t where t.user_id = :user_id and t.contents = :contents and t.hashtag_id = :hashtag_id")
  Optional<Todo> findByUserIdAndContentsAndHashtagId(@Param("user_id") Long user_id,
                                         @Param("contents") String contents,
                                         @Param("hashtag_id") Long hashtag_id);

}
