package com.ddudu.todo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pw;

    private String kakao_id;

    private String nick_name;

    @Column(nullable = false)
    private String init_authorization;
    private String image_url;

    @Column(nullable = false)
    private Long continuous_challenges_count;

    @Column(nullable = false)
    private Long successed_challenges_count;
    private Timestamp deleted_at;

}
