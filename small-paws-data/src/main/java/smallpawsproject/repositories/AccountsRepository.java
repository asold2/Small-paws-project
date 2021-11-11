package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smallpawsproject.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Long>
{
}
