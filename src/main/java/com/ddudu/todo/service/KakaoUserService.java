package com.ddudu.todo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ddudu.todo.model.KakaoProfile;
import com.ddudu.todo.model.User;
import com.ddudu.todo.model.oauth.OauthToken;
import com.ddudu.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class KakaoUserService {

    @Value("#{kakaoResource['rest_api_key']}")
    String client_id;

    @Value("#{kakaoResource['redirect_uri']}")
    String redirect_uri;

    @Autowired
    private final UserRepository userRepository;

    // access_token 발급받기 위한 카카오로 request 보내는 일련의 작업
    public OauthToken getAccessToken(String code) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_url", redirect_uri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, Object>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;

        try {
            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauthToken;
    }


    // 인가 코드로 발급받은 access_token으로 로그인한 사용자 정보 반환 받기
    public KakaoProfile getUserInfo(String token) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, Object>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> kakaoProfileResponse = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }

    // 로그인한 사용자 정보 반환받아 DB에 저장
    public User saveUser(String token) {

        // 카카오에서 access_token으로 사용자 프로필 가져오기
        KakaoProfile profile = getUserInfo(token);

        // 사용자 email로 user 존재하는지 확인
        User user = userRepository.findByEmail(profile.getKakao_account().getEmail());

        // 현재 로그인한 사용자 데이터가 DB에 없다면
        if(user == null) {

            user = User.builder()
                    .kakao_id(profile.getId().toString())
                    .image_url(profile.getKakao_account().getProfile().getProfile_image_url())
                    .nick_name(profile.getKakao_account().getProfile().getNickname())
                    .email(profile.getKakao_account().getEmail())
                    // 카카오 로그인 사용자라고 명시
                    .init_authorization("kakao")
                    .continuous_challenges_count(0L)
                    .successed_challenges_count(0L)
                    // 현재 시간 기록
                    .created_at(new Timestamp(System.currentTimeMillis()))
                    .build();

            userRepository.save(user);
        }

        return user;
    }

}