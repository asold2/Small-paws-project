package smallpawsproject.smallpawsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import smallpawsproject.smallpawsproject.repositories.AnimalRepository;

@Controller
public class AnimalController
{
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
