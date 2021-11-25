package smallpawsproject.services.impl;

import smallpawsproject.model.Animal;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.services.AnimalService;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    @Override public void addAnimal(Animal animal){
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }
}
