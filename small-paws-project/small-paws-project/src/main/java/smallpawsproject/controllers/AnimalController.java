package smallpawsproject.controllers;


import net.minidev.json.JSONArray;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import smallpawsproject.model.Animal;
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

  @RequestMapping(method = RequestMethod.PATCH, value="/new_information")
  @ResponseBody
//  public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal){
  public Animal updateAnimal(@RequestBody Animal animal){

    System.out.println("Updating animal");

//    Animal temp = animalServices.updateAnimal(animal);

//    return ResponseEntity.ok(animalServices.updateAnimal(animal));
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
