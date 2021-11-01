package smallpawsproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import smallpawsproject.model.AnimalAttendant;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.UserLogIn;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.repositories.UserLogInRepository;
import smallpawsproject.services.PetOwnerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@EnableJpaRepositories

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SmallPawsProjectApplication
{
	UserLogInRepository userLogInRepository;

	public SmallPawsProjectApplication(UserLogInRepository userLogInRepository)
	{
		this.userLogInRepository = userLogInRepository;
		userLogInRepository.save(new Veterinarian((long) 231101, "Bob", "password",
				UserLogIn.role.Veterinarian));
		userLogInRepository.save(
				new AnimalAttendant((long) 170901, "Alice", "123456", UserLogIn.role.AnimalAttendant));
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SmallPawsProjectApplication.class, args);
	}
	}

