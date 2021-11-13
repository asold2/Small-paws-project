package smallpawsproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;

import smallpawsproject.services.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LogInController
{
@Autowired
private UsersService usersService;






  @PostMapping("/account")
  @ResponseBody
  public int login(@RequestBody EndUser endUser){

    return usersService.check(endUser.getUserName(), endUser.getPassword());
    }
  }

