package smallpawsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import smallpawsproject.data_access.DataAccess;
import smallpawsproject.data_access.DataAccessImpl;
import smallpawsproject.repositories.*;
import smallpawsproject.rmi.Server;
import smallpawsproject.rmi.ServerImpl;
import smallpawsproject.services.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@EnableJpaRepositories
@SpringBootApplication
public class SmallPawsDataApplication {
		private final PetOwnerRepository petOwnerRepository;
		private final CertificateRepository certificateRepository;
		private final UsersRepository usersRepository;
		private final AnimalRepository animalRepository;
		private final AdoptionRequestRepository adoptionRequestRepository;

		private final ServiceFactory serviceFactory;
		private final DataAccess dataAccess;


//	@SuppressWarnings("CommentedOutCode")
	public SmallPawsDataApplication(PetOwnerRepository petOwnerRepository, CertificateRepository certificateRepository, UsersRepository usersRepository, AnimalRepository animalRepository, AdoptionRequestRepository adoptionRequestRepository){
		this.petOwnerRepository = petOwnerRepository;
		this.certificateRepository = certificateRepository;
		this.usersRepository = usersRepository;
		this.animalRepository = animalRepository;
		this.adoptionRequestRepository = adoptionRequestRepository;
		serviceFactory = new ServiceFactory(petOwnerRepository, usersRepository, animalRepository, certificateRepository, adoptionRequestRepository);
		dataAccess = DataAccessImpl.dataAccess();
		dataAccess.setServicefactory(serviceFactory);
//		try {
//			Naming.bind( "rmi://8070:1090/ServerTier3", server());
//		} catch (AlreadyBoundException | MalformedURLException | RemoteException e) {
//			e.printStackTrace();
//		}



/*
		adoptionRequestRepository.save(new AdoptionRequest(new Date(), animalRepository.getById(2), petOwnerRepository.getById(1), null, false, "Max"));
		for(int i=1; i<usersRepository.count(); i++){
			if(!(usersRepository.getById(i).getUserName().equals("Veterinarian") && usersRepository.getById(i).getUserName().equals("Attendant"))){
				usersRepository.save(new EndUser("Veterinarian", "veterinarian", "veterinarian@gmail.com", "Veterinarian"));
				usersRepository.save(new EndUser("Attendant", "attendant", "animal_attendant@gmail.com", "AnimalAttendant"));
			}
		}
		if(!(usersRepository.getById(2).getUserName().equals("Veterinarian") && usersRepository.getById(3).getUserName().equals("Attendant"))){

			}
*/
//		usersRepository.save(new Veterinarian("Veterinarian", "veterinarian", "veterinarian@gmail.com", "Veterinarian"));
//		usersRepository.save(new AnimalAttendant("Attendant", "attendant", "animal_attendant@gmail.com", "AnimalAttendant"));

	}



	@SuppressWarnings("RedundantThrows")
	@Bean Server server() throws RemoteException
	{
		return new ServerImpl(dataAccess);
	}

	@SuppressWarnings("deprecation")
	@Bean RmiServiceExporter exporter(Server implementation){
		Class<Server> serverInterface = Server.class;
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceInterface(serverInterface);
		exporter.setService(implementation);
		exporter.setServiceName("ServerTier3");
		exporter.setRegistryPort(1090);
		return exporter;

	}

	public static void main(String[] args) {
		SpringApplication.run(SmallPawsDataApplication.class, args);
	}
}
