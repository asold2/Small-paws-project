package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;

import smallpawsproject.services.*;

@RestController
public class LogInController
{

  private final UsersService usersService;

  /**
   * LogInController constructor
   * @param usersService is object of UserService
   */

  @Autowired
    public LogInController(UsersService usersService) {
    this.usersService = usersService;
    }

    /**
    * Method for checking users Username and Password
    * @param endUser is object of EndUser
    * @return
    */


    @PostMapping("/account")
    @ResponseBody
    public EndUser login(@RequestBody EndUser endUser){
    return usersService.check(endUser.getUserName(), endUser.getPassword());
    }

}



