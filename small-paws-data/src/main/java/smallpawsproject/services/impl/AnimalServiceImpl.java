package smallpawsproject.services.impl;

import org.hibernate.Session;
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

    @Override
    public Animal updateAnimal(Animal animal) {
        Animal temp = animalRepository.getById(animal.getId());
        temp.set(animal.getPicture(),animal.getAge(),animal.getSex(), animal.getDescription(), animal.isWashed(), animal.isFed(), animal.isVaccinated(), animal.getHealthNotes());
        animalRepository.save(temp);
        return temp;


    }
}
