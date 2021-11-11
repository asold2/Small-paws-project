package smallpawsproject;

import smallpawsproject.dataaccess.DataAccess;
import smallpawsproject.dataaccess.DataAccessImpl;
import smallpawsproject.model.Account;
import smallpawsproject.model.Employee;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.repositories.AccountsRepository;
import smallpawsproject.repositories.EmployeeRepository;
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
		private final AccountsRepository accountsRepository;
		private final EmployeeRepository employeeRepository;

		private ServiceFactory serviceFactory;
		private DataAccess dataAccess;

	public SmallPawsDataApplication(PetOwnerRepository petOwnerRepository, AccountsRepository accountsRepository, EmployeeRepository employeeRepository){
		this.petOwnerRepository = petOwnerRepository;
		this.accountsRepository = accountsRepository;
		this.employeeRepository = employeeRepository;
		serviceFactory = new ServiceFactory(petOwnerRepository, accountsRepository);
		dataAccess = new DataAccessImpl(serviceFactory);

		Employee emp = new Employee( "Mary", "pass", Employee.role.Veterinarian);
		employeeRepository.save(emp);
		System.out.println(emp);
//		accountsRepository.save(emp.getAccount());
		System.out.println(employeeRepository.findAll().get(0).getUserName());
		System.out.println(accountsRepository.findAll().get(0).getId());
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
