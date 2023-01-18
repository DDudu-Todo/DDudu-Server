package com.ddudu.todo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String nick_name;
    private String image_url;
    private Long continuous_challenges_count;
    private Long successed_challenges_count;
}
