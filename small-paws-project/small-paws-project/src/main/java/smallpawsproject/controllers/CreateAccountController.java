package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.RegistrationService;
//import main.java.smallpawsproject.repositories.PetOwnerRepository;

@RestController
public class CreateAccountController
{
  @Autowired
  private final RegistrationService registrationService;

  public CreateAccountController(RegistrationService registrationService)
  {
    this.registrationService = registrationService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  @ResponseBody
  public void registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");
    PetOwner petOwneToCreate = new PetOwner(petOwner.getId(), petOwner.getFirstName(), petOwner.getLastName(), petOwner.getAge(), petOwner.getSex(), petOwner.getFamilyStatus(), petOwner.getAvgIncome(), petOwner.getAdress(), petOwner.getJobTitle(), petOwner.getUserName(), petOwner.getPassword());
    registrationService.registerPetOwner(petOwneToCreate);
    System.out.println(petOwneToCreate.toString());
  }




}
