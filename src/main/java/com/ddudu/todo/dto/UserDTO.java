package com.ddudu.todo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long user_id;
    private String email;
    private String nickname;
    private String image_url;
}
