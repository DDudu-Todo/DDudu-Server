package com.ddudu.todo.service;

import com.ddudu.todo.model.oauth.AccessTokenValid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ddudu.todo.model.KakaoProfile;
import com.ddudu.todo.model.User;
import com.ddudu.todo.model.oauth.OauthToken;
import com.ddudu.todo.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.header.CrossOriginEmbedderPolicyServerHttpHeadersWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class KakaoUserService {

    @Value("#{kakaoResource['rest_api_key']}")
    String client_id;

    @Value("#{kakaoResource['redirect_uri']}")
    String redirect_uri;

    @Value("#{jwtResource['EXPIRATION_TIME']}")
    int JWT_EXPIRATION_TIME;

    @Value("#{jwtResource['SECRET']}")
    String JWT_SECRET;

    @Value("#{jwtResource['TOKEN_PREFIX']}")
    String JWT_TOKEN_PREFIX;

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

        System.out.println("kakaoProfile: " + kakaoProfile);
        return kakaoProfile;
    }

    // 로그인한 사용자 정보 반환받아 DB에 저장
    public Long SaveUserAndGetToken(String token) {

        // 카카오에서 access_token으로 사용자 프로필 가져오기
        KakaoProfile profile = getUserInfo(token);

        // 사용자 email로 user 존재하는지 확인
        Optional<User> user = userRepository.findByEmail(profile.getKakao_account().getEmail());

        // 현재 로그인한 사용자 데이터가 DB에 없다면
        if(user.isEmpty()) {
            User new_user = User.builder()
                    .kakao_id(profile.getId())
                    .image_url(profile.getKakao_account().getProfile().getProfile_image_url())
                    .nickname(profile.getKakao_account().getProfile().getNickname())
                    .email(profile.getKakao_account().getEmail())
                    // 카카오 로그인 사용자라고 명시
                    .init_authorization("kakao")
                    .build();

            System.out.println("user: " + new_user.toString());

            userRepository.save(new_user);

            return new_user.getUser_id();
        }

        return user.get().getUser_id();
    }

    public String createToken(User user) {

        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", user.getEmail());

        Date expireDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS)
        );

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public AccessTokenValid isAccessTokenValid(String token) {

        // 전달받은 토큰에서 필요한 정보만 가져오기
        token = token.replace(JWT_TOKEN_PREFIX, "");
        // System.out.println("token: " + token); (확인 완료)

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN_PREFIX + token);

        HttpEntity<MultiValueMap<String, Object>> tokenValidRequest = new HttpEntity<>(headers);
        ResponseEntity<String> isTokenValid = restTemplate.exchange(
                "https://kapi.kakao.com/v1/user/access_token_info",
                HttpMethod.GET,
                tokenValidRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenValid accessTokenValid = null;
        try {
            accessTokenValid = objectMapper.readValue(isTokenValid.getBody(), AccessTokenValid.class);

            if (accessTokenValid != null) {
                return accessTokenValid;
            } else {
                return null;
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByToken(String token) {


        AccessTokenValid validToken = isAccessTokenValid(token);

        if (validToken == null) {
            return null;
        }

        Optional<User> user = userRepository.findByKakao_id(validToken.getId());

        return user.orElse(null);

//        try {
//
//            // 토큰에서 이메일 정보 가져오기
//            email = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .get("email")
//                    .toString();
//        } catch (JwtException e) {
//            e.printStackTrace();
//        }
    }

}
