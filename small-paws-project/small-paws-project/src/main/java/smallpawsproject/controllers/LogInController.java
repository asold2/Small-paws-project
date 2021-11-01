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
  private final UserLogInRepository userLogInRepository;
  @Autowired
  private final PetOwnerService petOwnerService;


  public LogInController(UserLogInRepository userLogInRepository,
      PetOwnerService petOwnerService)
  {
    this.userLogInRepository = userLogInRepository;
    this.petOwnerService = petOwnerService;
  }
  @GetMapping("/loginPetOwner")
  @ResponseBody
  public AuthResponseInterface loginPetOwner(@RequestBody AuthRequest request)
      throws JsonProcessingException
  {
//    if(request.getUsername().equals("admin") && request.getPassword().equals("admin")){
////      String jwtToken = JWTProvider.createSimpleToken(request.getUsername());
//
//
//      System.out.println(new AuthResponse("asdgfhrwtaskjbfliua478tlaf"));
//      return new AuthResponse();
//    }
//    System.out.println(new AuthErrorResponse("Bad credentials"));
//    petOwnerService.authenticatePetOwner(request.getUsername(), request.getPassword());
    return new AuthResponse(petOwnerService.authenticatePetOwner(request.getUserName(), request.getPassword()));



  }



}
