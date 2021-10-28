package smallpawsproject.smallpawsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import smallpawsproject.smallpawsproject.model.UserLogIn;
import smallpawsproject.smallpawsproject.repositories.UserLogInRepository;

@Controller
public class LogInController
{
  private final UserLogInRepository userLogInRepository;


  public LogInController(UserLogInRepository userLogInRepository)
  {
    this.userLogInRepository = userLogInRepository;
  }

//  @RequestMapping("/validateLogin")
//  @RequestBody
//  public

}
