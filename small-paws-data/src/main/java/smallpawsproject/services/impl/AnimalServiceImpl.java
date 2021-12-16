package smallpawsproject.services.impl;

import smallpawsproject.model.Animal;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.services.AnimalService;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    /**
     * This the constructor of the class that initializes the repository that will save data in the database.
     * @param animalRepository
     */
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

    /**
     * This method has a parameter of the animal which carries the new info
     * for the animal hat has to be updated.
     * @param animal
     */
    @Override
    public void updateAnimal(Animal animal) {
        Animal temp = animalRepository.getById(animal.getId());
        temp.set(animal.getPicture(),animal.getAge(),animal.getSex(), animal.getDescription(), animal.isWashed(), animal.isFed(), animal.isVaccinated(), animal.getHealthNotes());
        animalRepository.save(temp);

    }
}
