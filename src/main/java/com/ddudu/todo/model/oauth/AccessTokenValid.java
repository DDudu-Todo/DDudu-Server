package com.ddudu.todo.model.oauth;

import lombok.Data;

@Data
public class AccessTokenValid {

  private Long id;
  private Integer expires_in;
  private Integer app_id;

  // Deprecated
  private Integer expiresInMillis;
  private Integer appId;
}
