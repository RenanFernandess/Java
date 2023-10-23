package com.renan.mailsend.mailsend.login;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.mailsend.mailsend.service.mail.SendMail;

@RestController
@RequestMapping("/login")
public class LoginController {
  private UUID token;

  @Autowired
  private SendMail sendMail;
  
  @PostMapping
  public ResponseEntity<LoginModel> login(@RequestBody LoginModel loginModel) {
    boolean isEmailValid = loginModel.getEmail().equals("ren@gmail.com") && loginModel.getPassword().equals("123456");

    
    if (isEmailValid) {
      this.token = UUID.randomUUID();
      System.out.println(this.token);

      sendMail.send(loginModel.getEmail(),loginModel.getEmail(), "Login", "Token: " + this.token);

      return new ResponseEntity<LoginModel>(loginModel, HttpStatus.OK);
    }
    return new ResponseEntity<LoginModel>(loginModel, HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/validate")
  public ResponseEntity<LoginModel> validate(@RequestBody LoginModel loginModel) {
    boolean isTokenValid = loginModel.getToken().equals(this.token);

    if (isTokenValid) {
      this.token = UUID.randomUUID();
      return new ResponseEntity<LoginModel>(loginModel, HttpStatus.OK);
    }
    return new ResponseEntity<LoginModel>(loginModel, HttpStatus.UNAUTHORIZED);
  }
}
