package smallpawsproject.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  //public route(without being authorized by spring)
  @PostMapping("/newAccount")
  @ResponseBody
  public int registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");

    var petOwnerToCreate = new PetOwner(petOwner.getJobTitle(),
        petOwner.getAddress(), petOwner.getZipcode(), petOwner.getAge(), petOwner.getAvgIncome(), petOwner.getFamilyStatus(), petOwner.getFirstName(),
        petOwner.getLastName(), petOwner.getSex(), petOwner.getId(), petOwner.getUserName(), petOwner.getPassword(), petOwner.getEmail());
    return petOwnerService.registerPetOwner((PetOwner) petOwnerToCreate);

  }






}
