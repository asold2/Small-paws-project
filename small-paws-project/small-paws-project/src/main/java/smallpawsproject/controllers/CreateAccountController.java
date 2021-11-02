package smallpawsproject.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONPropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
  @RequestMapping(method = RequestMethod.POST, value = "/register")
  @ResponseBody
  public int registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");

    var petOwnerToCreate = new PetOwner(petOwner.getId(), petOwner.getFirstName(), petOwner.getLastName(), petOwner.getAge(), petOwner.getSex(), petOwner.getFamilyStatus(), petOwner.getAvgIncome(), petOwner.getAdress(), petOwner.getJobTitle(), petOwner.getUserName(), petOwner.getPassword());
    return petOwnerService.registerPetOwner(petOwnerToCreate);

  }






}
