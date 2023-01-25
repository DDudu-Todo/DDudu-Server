package com.ddudu.todo.model;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

// 테이블 생성을 하지 않는 Entity
@MappedSuperclass
// JPA의 동작을 감시
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class Base {

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp created_at;

  @UpdateTimestamp
  private Timestamp updated_at;
}
