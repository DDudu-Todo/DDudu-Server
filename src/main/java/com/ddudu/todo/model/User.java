package com.ddudu.todo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String email;

    private String pw;

    private String kakao_id;

    private String nick_name;

    @Column(nullable = false)
    private String init_authorization;
    private String image_url;

    private Timestamp deleted_at;

}
