package com.ddudu.todo.repository;

import com.ddudu.todo.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

  @Query("select h.hashtag_id from Hashtag h where h.contents = :contents")
  Long findByContents(@Param("contents") String contents);

}
