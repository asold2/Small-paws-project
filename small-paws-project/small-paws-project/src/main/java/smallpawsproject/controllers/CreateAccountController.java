package smallpawsproject.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.catalina.valves.JsonErrorReportValve;
import org.json.JSONPropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.PetOwnerService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CreateAccountController
{
  @Autowired
  private final PetOwnerService petOwnerService;

  public CreateAccountController(PetOwnerService registrationService)
  {
    this.petOwnerService = registrationService;
  }

  @PostMapping("/userName")
  @ResponseBody
  public int checkUserName(@RequestBody EndUser user)
  {
    String text = user.getUserName();
    return petOwnerService.checkUsername(text);
  }


  //public route(without being authorized by spring)
  @PostMapping("/newAccount")
  @ResponseBody
  public int registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");
//    System.out.println(petOwner.getId());
//    System.out.println(petOwner.getUserId());
//    System.out.println();

    var petOwnerToCreate = new PetOwner(petOwner.getJobTitle(),
        petOwner.getAddress(), petOwner.getZipcode(), petOwner.getAge(), petOwner.getAvgIncome(), petOwner.getFamilyStatus(), petOwner.getFirstName(),
        petOwner.getLastName(), petOwner.getSex(), petOwner.getId(), petOwner.getUserName(), petOwner.getPassword(), petOwner.getEmail(), "PetOwner");

    System.out.println(petOwnerToCreate.getUserId());
    System.out.println(petOwnerToCreate.getId());


    return petOwnerService.registerPetOwner((PetOwner) petOwnerToCreate);

  }






}
