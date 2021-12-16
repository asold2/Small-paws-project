package smallpawsproject.rmi;

import org.springframework.stereotype.Component;
import java.rmi.RemoteException;

@Component
public class ClientFactory
{
  private ClientRMI client;

  public ClientRMI getClient(){
    if(client==null){
      try
      {
        client = new ClientRMIImpl();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
      return client;
    }
    return client;
  }
}
