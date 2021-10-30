package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smallpawsproject.repositories.UserLogInRepository;
@RestController

public class LogInController
{
  @Autowired
  private final UserLogInRepository userLogInRepository;


  public LogInController(UserLogInRepository userLogInRepository)
  {
    this.userLogInRepository = userLogInRepository;
  }

//  @RequestMapping("/validateLogin")
//  @RequestBody
//  public

}
