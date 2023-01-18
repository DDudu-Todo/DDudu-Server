package com.ddudu.todo.repository;

import com.ddudu.todo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void 사용자_삽입() {
    for (int i = 0; i < 50; i++) {
      User user = User.builder()
              .email("user"+i+"@kakao.com")
              .pw("1234")
              .nick_name("user"+i)
              .init_authorization("local")
              .image_url("default.png")
              .continuous_challenges_count(0L)
              .successed_challenges_count(0L)
              .build();

      userRepository.save(user);
    }
  }

  @Test
  public void 사용자_모두_가져오기() {
    List<User> list = userRepository.findAll();

    for (User user : list) {
      System.out.println(user.toString());
    }
  }

  @Test
  public void 사용자_1개_가져오기() {
    Optional<User> result = userRepository.findById(9L);
    if (result.isPresent()) {
      System.out.println(result.get());
    } else {
      System.out.println("존재하지 않는 user id");
    }
  }
}
