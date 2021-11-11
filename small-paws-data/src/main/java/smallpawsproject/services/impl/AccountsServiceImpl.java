package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.model.Account;
import smallpawsproject.repositories.AccountsRepository;
import smallpawsproject.services.AccountsService;

import java.util.List;

public class AccountsServiceImpl implements AccountsService
{

  @Autowired
  private final AccountsRepository accountsRepository;

  public AccountsServiceImpl(AccountsRepository accountsRepository)
  {
    this.accountsRepository = accountsRepository;
  }

  @Override public List<Account> getAccounts()
  {
    return accountsRepository.findAll();
  }
}
