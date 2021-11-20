package smallpawsproject.controllers;

import antlr.collections.List;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;

import smallpawsproject.model.PetOwner;
import smallpawsproject.services.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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

