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
	public static void main(String[] args) {
		SpringApplication.run(SmallPawsDataApplication.class, args);
	}
}
