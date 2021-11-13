package smallpawsproject;

import smallpawsproject.dataaccess.DataAccess;
import smallpawsproject.dataaccess.DataAccessImpl;
import smallpawsproject.model.AnimalAttendant;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.rmi.Server;
import smallpawsproject.rmi.ServerImpl;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@EnableJpaRepositories
@SpringBootApplication
public class SmallPawsDataApplication {
		private final PetOwnerRepository petOwnerRepository;
		private final UsersRepository usersRepository;

		private ServiceFactory serviceFactory;
		private DataAccess dataAccess;

	public SmallPawsDataApplication(PetOwnerRepository petOwnerRepository, UsersRepository usersRepository){
		this.petOwnerRepository = petOwnerRepository;
		this.usersRepository = usersRepository;
		serviceFactory = new ServiceFactory(petOwnerRepository, usersRepository);
		dataAccess = new DataAccessImpl(serviceFactory);



	}



	@Bean Server server() throws RemoteException
	{
		return new ServerImpl(dataAccess);
	}

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
