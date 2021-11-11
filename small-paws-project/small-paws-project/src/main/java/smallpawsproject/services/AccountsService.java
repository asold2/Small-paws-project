package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.Account;

@Service
public interface AccountsService
{
   int checkAccount(String userName, String password);
}
