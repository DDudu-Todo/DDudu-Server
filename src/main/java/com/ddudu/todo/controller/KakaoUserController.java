package com.ddudu.todo.controller;

import com.ddudu.todo.dto.UserDTO;
import com.ddudu.todo.model.User;
import com.ddudu.todo.model.oauth.OauthToken;
import com.ddudu.todo.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KakaoUserController {

    @Value("#{jwtResource['HEADER_STRING']}")
    String JWT_HEADER_STRING;

    @Value("#{jwtResource['TOKEN_PREFIX']}")
    String JWT_TOKEN_PREFIX;

    private final KakaoUserService kakaoUserService;

    // front 에서 인가코드를 받아오는 url
    @GetMapping("/oauth/token")
    public ResponseEntity getLogin(@RequestParam("code") String code) {
        // front 에서 받은 인가코드로 카카오로부터 access_token 발급
        OauthToken access_token = kakaoUserService.getAccessToken(code);

        // access_token을 받았다면 로그인 완료라는 뜻으로, DB에 저장하고 완료되었다는 뜻으로 user_id 반환받기
        Long user_id = kakaoUserService.SaveUserAndGetToken(access_token.getAccess_token());

        if (user_id != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(JWT_HEADER_STRING, JWT_TOKEN_PREFIX + access_token.getAccess_token());

            return ResponseEntity.ok().headers(headers).body("success");
        } else {
            return ResponseEntity.internalServerError().body("fail");
        }

    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token) {

        // System.out.println("header token: " + token); (확인 완료)

        // front에서 받은 token으로 유저 정보 반환 받기
        User user = kakaoUserService.getUserByToken(token);

        // 아래 정보들을 응답 객체에 담아 보낸다.
        UserDTO dto = UserDTO.builder()
                .user_id(user.getUser_id())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .image_url(user.getImage_url())
                .build();

        return ResponseEntity.ok().body(dto);
    }
}
