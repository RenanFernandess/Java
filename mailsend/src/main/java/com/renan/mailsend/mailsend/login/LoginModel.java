package com.renan.mailsend.mailsend.login;

import java.util.UUID;

import lombok.Data;

@Data
public class LoginModel {
  private String email;
  private String password;
  private UUID token;
}
