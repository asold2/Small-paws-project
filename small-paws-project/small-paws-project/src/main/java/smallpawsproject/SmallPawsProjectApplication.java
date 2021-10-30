package smallpawsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import smallpawsproject.model.AnimalAttendant;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.repositories.UserLogInRepository;

@EnableJpaRepositories

@SpringBootApplication
public class SmallPawsProjectApplication {
	UserLogInRepository userLogInRepository;

	public SmallPawsProjectApplication(UserLogInRepository userLogInRepository)
	{
		this.userLogInRepository = userLogInRepository;
		userLogInRepository.save(new Veterinarian((long) 231101,"Bob", "password"));
		userLogInRepository.save(new AnimalAttendant((long) 170901,"Alice", "123456"));
	}

	public static void main(String[] args) {

		SpringApplication.run(SmallPawsProjectApplication.class, args);
	}

}
