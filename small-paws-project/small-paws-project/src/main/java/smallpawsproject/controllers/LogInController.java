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
  @GetMapping("/loginPetOwner")
  @ResponseBody
  public AuthResponseInterface loginPetOwner(@RequestBody AuthRequest request)
      throws JsonProcessingException
  {
    return new AuthResponse(petOwnerService.authenticatePetOwner(request.getUserName(), request.getPassword()));
  }

  @GetMapping("/loginEmployee")
  @ResponseBody
  public AuthResponse loginEmployee(@RequestBody AuthRequest request){
    return new AuthResponse(employeeService.authenticateEmployee(
        request.getUserName(), request.getPassword()));
  }



}
