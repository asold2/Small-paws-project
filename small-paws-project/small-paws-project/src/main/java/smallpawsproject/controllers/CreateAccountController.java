package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.PetOwnerService;

@RestController
public class CreateAccountController
{

  private  final PetOwnerService petOwnerService;

  @Autowired
  public CreateAccountController(PetOwnerService petOwnerService) {
    this.petOwnerService = petOwnerService;
  }


  @PostMapping("/userName")
  @ResponseBody
  public int checkUserName(@RequestBody EndUser user)
  {
    String text = user.getUserName();
    return petOwnerService.checkUsername(text);
  }


  @PostMapping("/newAccount")
  @ResponseBody
  public int registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");


    var petOwnerToCreate = new PetOwner(petOwner.getJobTitle(),
        petOwner.getAddress(), petOwner.getZipcode(), petOwner.getAge(), petOwner.getAvgIncome(), petOwner.getFamilyStatus(), petOwner.getFirstName(),
        petOwner.getLastName(), petOwner.getSex(), petOwner.getId(), petOwner.getUserName(), petOwner.getPassword(), petOwner.getEmail(), "PetOwner");

    System.out.println(petOwnerToCreate.getUserId());
    System.out.println(petOwnerToCreate.getId());


    return petOwnerService.registerPetOwner((PetOwner) petOwnerToCreate);

  }






}
