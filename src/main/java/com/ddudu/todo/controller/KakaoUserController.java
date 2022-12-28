package com.ddudu.todo.controller;

import com.ddudu.todo.model.User;
import com.ddudu.todo.model.oauth.OauthToken;
import com.ddudu.todo.service.KakaoUserService;
import com.ddudu.todo.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KakaoUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KakaoUserController.class);

    @Autowired
    private final KakaoUserService kakaoUserService;

    // front 에서 인가코드를 받아오는 url
    @GetMapping("/oauth/token")
    public User getLogin(@RequestParam("code") String code) {
        // front 에서 받은 인가코드로 카카오로부터 access_token 발급
        OauthToken access_token = kakaoUserService.getAccessToken(code);

        // access_token을 받았다면 로그인 완료라는 뜻으로, DB에 저장하고 해당 데이터 반환받기
        User user = kakaoUserService.saveUser(access_token.getAccess_token());

        // user가 성공적으로 저장되면 console에 출력하도록
        System.out.println(user.toString());

        return user;
    }


}
