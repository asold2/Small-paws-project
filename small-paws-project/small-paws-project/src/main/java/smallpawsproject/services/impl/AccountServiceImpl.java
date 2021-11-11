package smallpawsproject.services.impl;

import org.springframework.stereotype.Service;
import smallpawsproject.model.Account;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.AccountsService;

import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountsService
{
  ClientFactory clientFactory;
  ClientRMI client;
  List<Account> accounts;

  public AccountServiceImpl(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
    client = clientFactory.getClient();
    try
    {
      client.connect();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    try
    {
      accounts = client.getAccounts();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public int checkAccount(String userName, String password)
  {
    int answer = 0;
        for(Account account : accounts){
          if (account.getUserName().equals(userName) && account.getPassword()
              .equals(password))
          {
            System.out.println(account.getUserName());
            System.out.println(account.getPassword());
            answer = HttpServletResponse.SC_ACCEPTED;
            break;
          }
          else
          {
            answer = HttpServletResponse.SC_FORBIDDEN;
          }
        }


    return answer;

  }
}
