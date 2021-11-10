package smallpawsproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.repositories.*;
import smallpawsproject.securityjwt.http.AuthRequest;
import smallpawsproject.securityjwt.http.AuthResponse;
import smallpawsproject.securityjwt.http.AuthResponseInterface;
//import smallpawsproject.securityjwt.provider.JWTProvider;
import smallpawsproject.services.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LogInController
{
  @Autowired
  private final EmployeeService employeeService;
  @Autowired
  private final PetOwnerService petOwnerService;


  public LogInController(EmployeeService employeeService,
      PetOwnerService petOwnerService)
  {
    this.employeeService = employeeService;
    this.petOwnerService = petOwnerService;
  }
//  @GetMapping("/loginPetOwner")
//  @ResponseBody
//  public int loginPetOwner(@RequestBody AuthRequest request)
//      throws JsonProcessingException
//  {
//    return petOwnerService.authenticatePetOwner(request.getUserName(), request.getPassword());
//  }
//
//  @GetMapping("/loginEmployee")
//  @ResponseBody
//  public int loginEmployee(@RequestBody AuthRequest request){
//    return employeeService.authenticateEmployee(
//        request.getUserName(), request.getPassword());
//  }

  @PostMapping("/login")
  @ResponseBody
  public int login(@RequestBody AuthRequest request){
    if(request.getRole().equals("PetOwner")){
      return petOwnerService.authenticatePetOwner(request.getUserName(),
          request.getPassword());
    }
    else if(request.getRole().equals("Employee")){
      return employeeService.authenticateEmployee(request.getUserName(),
          request.getPassword());
    }
    return HttpServletResponse.SC_FORBIDDEN;
    }
  }

