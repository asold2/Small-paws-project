package smallpawsproject.controllers;


import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import smallpawsproject.model.Animal;
import smallpawsproject.services.AnimalServices;

@RestController
public class AnimalController
{

  private final AnimalServices animalServices;

  @Autowired
  public AnimalController(AnimalServices animalServices) {
    this.animalServices = animalServices;
  }

  @RequestMapping(method = RequestMethod.PATCH, value="/new_information")
  @ResponseBody
  public Animal updateAnimal(@RequestBody Animal animal){

    System.out.println("Updating animal");

  return animalServices.updateAnimal(animal);
  }


  @RequestMapping(method = RequestMethod.POST, value = "/animal")
  @ResponseBody
  public void AddAnimal(@RequestBody Animal animal)
  {
    System.out.println("Added animal");
    System.out.println(animal.toString());

    animalServices.AddAnimal(animal);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/animals")
  @ResponseBody
  public JSONArray getAnimals()
  {
    System.out.println("Animals are out");

    return animalServices.GetAnimals();
  }
}
