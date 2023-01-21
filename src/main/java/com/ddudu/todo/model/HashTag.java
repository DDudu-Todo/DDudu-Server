package com.ddudu.todo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hashtag")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Hashtag extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long hashtag_id;

  @Column(nullable = false, unique = true)
  private String contents;

}
