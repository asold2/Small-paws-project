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

  /**
   * CreateAccountController constructor
   * @param petOwnerService is object of PetOwnerService
   */

  @Autowired
  public CreateAccountController(PetOwnerService petOwnerService) {
    this.petOwnerService = petOwnerService;
  }

  /**
   * Method for checking if users username already exists
   * @param user is object of User
   * @return Method returns the checked username
   */


  @PostMapping("/userName")
  @ResponseBody
  public int checkUserName(@RequestBody EndUser user)
  {
    String text = user.getUserName();
    return petOwnerService.checkUsername(text);
  }

  /**
   * Method for making new object of PetOwner with all its necessary information
   * @param petOwner is object of PetOwner
   * @return method returns the new PetOwner object
   */


  @PostMapping("/newAccount")
  @ResponseBody
  public int registerPetOwner(@RequestBody PetOwner petOwner){

    var petOwnerToCreate = new PetOwner(petOwner.getJobTitle(),
        petOwner.getAddress(), petOwner.getZipcode(), petOwner.getAge(), petOwner.getAvgIncome(), petOwner.getFamilyStatus(), petOwner.getFirstName(),
        petOwner.getLastName(), petOwner.getSex(), petOwner.getId(), petOwner.getUserName(), petOwner.getPassword(), petOwner.getEmail(), "PetOwner");

    return petOwnerService.registerPetOwner(petOwnerToCreate);

  }






}
