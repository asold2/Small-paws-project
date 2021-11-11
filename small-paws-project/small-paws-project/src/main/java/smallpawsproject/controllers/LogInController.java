package smallpawsproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.Account;
import smallpawsproject.securityjwt.http.AuthRequest;
import smallpawsproject.securityjwt.http.AuthResponse;
import smallpawsproject.securityjwt.http.AuthResponseInterface;
//import smallpawsproject.securityjwt.provider.JWTProvider;
import smallpawsproject.services.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LogInController
{
//  @Autowired
//  private final EmployeeService employeeService;
//  @Autowired
//  private final PetOwnerService petOwnerService;
//
  @Autowired
  private AccountsService accountsService;


  public LogInController(AccountsService accountsService)
  {
    this.accountsService = accountsService;
  }


  @PostMapping("/account")
  @ResponseBody
  public int login(@RequestBody Account account){
//    if(request.getRole().equals("PetOwner")){
//      return petOwnerService.authenticatePetOwner(request.getUserName(),
//          request.getPassword());
//    }
//    else if(request.getRole().equals("Employee")){
//      return employeeService.authenticateEmployee(request.getUserName(),
//          request.getPassword());
//    }

    return accountsService.checkAccount(account.getUserName(),
        account.getPassword());
    }
  }

