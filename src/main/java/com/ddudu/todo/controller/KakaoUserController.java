package com.ddudu.todo.controller;

import com.ddudu.todo.dto.UserDTO;
import com.ddudu.todo.model.User;
import com.ddudu.todo.model.oauth.OauthToken;
import com.ddudu.todo.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KakaoUserController {

    @Value("#{jwtResource['HEADER_STRING']}")
    String JWT_HEADER_STRING;

    @Value("#{jwtResource['TOKEN_PREFIX']}")
    String JWT_TOKEN_PREFIX;

    private static final Logger LOGGER = LoggerFactory.getLogger(KakaoUserController.class);

    @Autowired
    private final KakaoUserService kakaoUserService;

    // front 에서 인가코드를 받아오는 url
    @GetMapping("/oauth/token")
    public ResponseEntity getLogin(@RequestParam("code") String code) {
        // front 에서 받은 인가코드로 카카오로부터 access_token 발급
        OauthToken access_token = kakaoUserService.getAccessToken(code);

        // access_token을 받았다면 로그인 완료라는 뜻으로, DB에 저장하고 해당 데이터 반환받기
        // 발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 를 생성
        String jwtToken = kakaoUserService.SaveUserAndGetToken(access_token.getAccess_token());

        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_HEADER_STRING, JWT_TOKEN_PREFIX + jwtToken);

        return ResponseEntity.ok().headers(headers).body("success");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam("token") String token) {

        // front에서 받은 token으로 유저 정보 반환 받기
        User user = kakaoUserService.getUserByToken(token);

        // 아래 정보들을 응답 객체에 담아 보낸다.
        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nick_name(user.getNick_name())
                .image_url(user.getImage_url())
                .continuous_challenges_count(user.getContinuous_challenges_count())
                .successed_challenges_count(user.getSuccessed_challenges_count())
                .build();

        return ResponseEntity.ok().body(dto);
    }

}
