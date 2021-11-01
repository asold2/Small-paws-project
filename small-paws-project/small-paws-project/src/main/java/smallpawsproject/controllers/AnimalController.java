package smallpawsproject.controllers;


import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import smallpawsproject.model.Animal;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.services.AnimalServices;

import java.util.ArrayList;

@RestController
public class AnimalController
{
  @Autowired
  private final AnimalServices animalServices;


  public AnimalController(AnimalServices animalServices)
  {
    this.animalServices = animalServices;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/animal")
  @ResponseBody
  public void AddAnimal(@RequestBody Animal animal)
  {
    System.out.println("Added animal");

    //Animal animalToAdd = new Animal(animal.getTypeOfAnimal(),animal.getAge(),animal.getDescription());
    animalServices.AddAnimal(animal);
    //System.out.println(animalToAdd.getTypeOfAnimal());
  }

  @RequestMapping(method = RequestMethod.GET, value = "animals")
  @ResponseBody
  public JSONArray getAnimals()
  {
    System.out.println("Animals are out");
    return animalServices.GetAnimals();
  }
}
