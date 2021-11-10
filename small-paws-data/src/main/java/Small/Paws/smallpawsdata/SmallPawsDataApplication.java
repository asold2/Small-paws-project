package Small.Paws.smallpawsdata;

import Small.Paws.smallpawsdata.dataaccess.DataAccess;
import Small.Paws.smallpawsdata.dataaccess.DataAccessImpl;
import Small.Paws.smallpawsdata.rmi.Server;
import Small.Paws.smallpawsdata.rmi.ServerImpl;
import Small.Paws.smallpawsdata.repositories.PetOwnerRepository;
import Small.Paws.smallpawsdata.services.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiServiceExporter;

import static Small.Paws.smallpawsdata.rmi.Server.ServerTier3;

@EnableJpaRepositories
@SpringBootApplication
public class SmallPawsDataApplication {
		private PetOwnerRepository petOwnerRepository;


		private ServiceFactory serviceFactory;
		private DataAccess dataAccess;

	public SmallPawsDataApplication(PetOwnerRepository petOwnerRepository){
		this.petOwnerRepository = petOwnerRepository;
		serviceFactory = new ServiceFactory(petOwnerRepository);
		dataAccess = new DataAccessImpl(serviceFactory);
	}



	@Bean Server server(){
		return new ServerImpl(dataAccess);
	}

	@Bean RmiServiceExporter exporter(Server implementation){
		Class<Server> serverInterface = Server.class;
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceInterface(serverInterface);
		exporter.setService(implementation);
		exporter.setServiceName(ServerTier3);
		exporter.setRegistryPort(1090);
		return exporter;

	}

	public static void main(String[] args) {
//
//		Server server = null;
//		try
//		{
//			server = new ServerImpl();
//			Naming.rebind("ServerTier3", (Remote)server);
//		}
//		catch (RemoteException e)
//		{
//			e.printStackTrace();
//		}
//		catch (MalformedURLException e)
//		{
//			e.printStackTrace();
//		}
		SpringApplication.run(SmallPawsDataApplication.class, args);
	}
}
