package smallpawsproject;

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


	public SmallPawsDataApplication(PetOwnerRepository petOwnerRepository, CertificateRepository certificateRepository, UsersRepository usersRepository, AnimalRepository animalRepository, AdoptionRequestRepository adoptionRequestRepository){
		this.petOwnerRepository = petOwnerRepository;
		this.certificateRepository = certificateRepository;
		this.usersRepository = usersRepository;
		this.animalRepository = animalRepository;
		this.adoptionRequestRepository = adoptionRequestRepository;
		serviceFactory = new ServiceFactory(petOwnerRepository, usersRepository, animalRepository, certificateRepository, adoptionRequestRepository);
		dataAccess = DataAccessImpl.dataAccess();
		dataAccess.setServicefactory(serviceFactory);

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
