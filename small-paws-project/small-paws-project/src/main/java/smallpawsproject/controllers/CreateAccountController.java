package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.PetOwnerService;

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
  public void registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");

    PetOwner petOwneToCreate = new PetOwner(petOwner.getId(), petOwner.getFirstName(), petOwner.getLastName(), petOwner.getAge(), petOwner.getSex(), petOwner.getFamilyStatus(), petOwner.getAvgIncome(), petOwner.getAdress(), petOwner.getJobTitle(), petOwner.getUserName(), petOwner.getPassword());
    petOwnerService.registerPetOwner(petOwneToCreate);
  }






}
