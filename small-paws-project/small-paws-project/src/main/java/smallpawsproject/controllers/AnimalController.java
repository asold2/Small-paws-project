package smallpawsproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import smallpawsproject.repositories.AnimalRepository;

@RestController
public class AnimalController
{
  @Autowired
  private final AnimalRepository animalRepository;

  public AnimalController(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @RequestMapping("/animals")
  public String getAnimals(Model model){
    model.addAttribute("animals", animalRepository.findAll());
    return "";
  }
}
