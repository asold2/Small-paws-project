package smallpawsproject.smallpawsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import smallpawsproject.smallpawsproject.model.PetOwner;
import smallpawsproject.smallpawsproject.repositories.PetOwnerRepository;

@Controller
public class CreateAccountController
{
  private final PetOwnerRepository petOwnerRepository;

  public CreateAccountController(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @RequestMapping("/createAccount")
  public void CreateAccount(PetOwner petOwner)
  {

  }
}
