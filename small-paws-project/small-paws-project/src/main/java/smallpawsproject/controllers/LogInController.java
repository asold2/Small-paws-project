package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;

import smallpawsproject.services.*;

@RestController
public class LogInController
{

private final UsersService usersService;

  @Autowired
  public LogInController(UsersService usersService) {
    this.usersService = usersService;
  }


  @PostMapping("/account")
  @ResponseBody
  public EndUser login(@RequestBody EndUser endUser){
    return usersService.check(endUser.getUserName(), endUser.getPassword());
    }
  }

