package Small.Paws.smallpawsdata;

import Small.Paws.smallpawsdata.model.Animal;
import Small.Paws.smallpawsdata.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SmallPawsDataApplication {
	@Autowired
	private final AnimalRepository animalRepository;

	public SmallPawsDataApplication(AnimalRepository animalRepository){
		this.animalRepository = animalRepository;
		//animalRepository.save(new Animal("Dog", 13, "Good boy"));

		System.out.println(animalRepository.findAll().get(0).getID());
		int stop=0;
	}

	public static void main(String[] args) {
		SpringApplication.run(SmallPawsDataApplication.class, args);
	}

}
