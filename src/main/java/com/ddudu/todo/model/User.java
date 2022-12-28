package com.ddudu.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String pw;
    private String kakao_id;
    private String nick_name;
    private String init_authorization;
    private String image_url;
    private Long continuous_challenges_count;
    private Long successed_challenges_count;
    private Timestamp deleted_at;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", kakao_id='" + kakao_id + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", init_authorization='" + init_authorization + '\'' +
                ", image_url='" + image_url + '\'' +
                ", continuous_challenges_count=" + continuous_challenges_count +
                ", successed_challenges_count=" + successed_challenges_count +
                ", deleted_at=" + deleted_at +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    @CreationTimestamp
    private Timestamp created_at;
    private Timestamp updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getInit_authorization() {
        return init_authorization;
    }

    public void setInit_authorization(String init_authorization) {
        this.init_authorization = init_authorization;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getContinuous_challenges_count() {
        return continuous_challenges_count;
    }

    public void setContinuous_challenges_count(Long continuous_challenges_count) {
        this.continuous_challenges_count = continuous_challenges_count;
    }

    public Long getSuccessed_challenges_count() {
        return successed_challenges_count;
    }

    public void setSuccessed_challenges_count(Long successed_challenges_count) {
        this.successed_challenges_count = successed_challenges_count;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Builder
    public User(String email,
                String pw,
                String kakao_id,
                String nick_name, String init_authorization,
                String image_url,
                Long continuous_challenges_count,
                Long successed_challenges_count,
                Timestamp deleted_at,
                Timestamp created_at,
                Timestamp updated_at) {
        this.email = email;
        this.pw = pw;
        this.kakao_id = kakao_id;
        this.nick_name = nick_name;
        this.init_authorization = init_authorization;
        this.image_url = image_url;
        this.continuous_challenges_count = continuous_challenges_count;
        this.successed_challenges_count = successed_challenges_count;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
