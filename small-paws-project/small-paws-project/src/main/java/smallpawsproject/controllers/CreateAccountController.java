package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;

import java.io.File;
import java.util.List;
//import main.java.smallpawsproject.repositories.PetOwnerRepository;

@RestController
public class CreateAccountController
{
  @Autowired
  private final PetOwnerRepository petOwnerRepository;


  public CreateAccountController(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  @ResponseBody
  public void registerPetOwner(@RequestBody PetOwner petOwner){
    System.out.println("Registering pet owner");
    PetOwner petOwneToCreate = new PetOwner(petOwner.getFirstName(), petOwner.getLastName(), petOwner.getAge(), petOwner.getSex(), petOwner.getFamilyStatus(), petOwner.getAvgIncome(), petOwner.getAdress(), petOwner.getJobTitle(), petOwner.getUserName(), petOwner.getPassword());
    petOwnerRepository.save(petOwneToCreate);
  }

  @GetMapping(value = "/petowners")
  public Iterable<PetOwner> getPetOwners(){
  return petOwnerRepository.findAll();
  }
}
