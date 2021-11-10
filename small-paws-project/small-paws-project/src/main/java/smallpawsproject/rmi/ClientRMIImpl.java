package smallpawsproject.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import smallpawsproject.model.PetOwner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static smallpawsproject.rmi.Server.ServerTier3;

public class ClientRMIImpl extends UnicastRemoteObject implements ClientRMI
{
  @Autowired
  private Server server;

  public ClientRMIImpl() throws RemoteException
  {
    try
    {
//      System.setProperty("java.security.policy","security.policy");
      Naming.rebind(CLIENT_SERVICE_NAME, this);
      System.out.println("Client started");
    }
    catch (MalformedURLException | RemoteException e)
    {
      e.printStackTrace();
    }

//    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.getRegistry("localhost", 1090);
    try
    {
      server = (Server)registry.lookup("ServerTier3");
      System.out.println("Connected to server");
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
//        try
//    {
////      System.setSecurityManager(new RMISecurityManager());
////      System.setProperty("java.security.policy", "src/main/java/smallpawsproject/rmi/security.policy");
//      server = (Server) Naming.lookup(ServerTier3);
//      System.out.println("Connected to server");
//    }
//        catch (NotBoundException | MalformedURLException e)
//        {
//          e.printStackTrace();
//        }

  }

//  @Bean RmiProxyFactoryBean service(){
//    RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
//    rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1090/ServerTier3");
//    rmiProxyFactoryBean.setServiceInterface(Server.class);
//    return rmiProxyFactoryBean;
//  }




  @Override public int registerPetOwner(PetOwner petOwner)
      throws RemoteException
  {
    return 0;
  }


  //  @Override public void start()
//  {
//    ClientRMI clientRMI = (ClientRMI) SpringApplication.run(ClientRMI.class).getBean(Server.class);
//    System.out.println("Client started");
//
//  }

  @Override public String test()
  {
    try
    {
      return server.test();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return "Test failed";
  }

}
