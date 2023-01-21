package com.ddudu.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

// web 보안 활성화
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CorsFilter corsFilter;

	@Bean
	// 정적 파일 제외하고 spring security 실행
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	// 비밀번호 암호화
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    // 기존의 httpSecurity 역할
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .addFilter(corsFilter)

                .authorizeRequests((req) -> req
                        // /main으로 들어오는 요청은 모두 인가(authenticated) 되어야 한다.
                        .antMatchers("/main/**").authenticated()
                        // 그 외의 요청(anyRequest)은 인증없이 접근 허용
                        .anyRequest().permitAll()
                )

                .build();

    }
}