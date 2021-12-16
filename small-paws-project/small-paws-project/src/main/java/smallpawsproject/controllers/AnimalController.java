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

  /**
   * AnimalController constructor
   * @param animalServices is object of AnimalServices
   */

  @Autowired
  public AnimalController(AnimalServices animalServices) {
    this.animalServices = animalServices;
  }

  /**
   * Method for updating information about specific animal
   * @param animal is objject of Animal
   */

  @RequestMapping(method = RequestMethod.PATCH, value="/new_information")
  @ResponseBody
  public void updateAnimal(@RequestBody Animal animal){

    animalServices.updateAnimal(animal);
  }


  @RequestMapping(method = RequestMethod.POST, value = "/animal")
  @ResponseBody
  public void AddAnimal(@RequestBody Animal animal)
  {

    animalServices.AddAnimal(animal);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/animals")
  @ResponseBody
  public JSONArray getAnimals()
  {

    return animalServices.GetAnimals();
  }
}
