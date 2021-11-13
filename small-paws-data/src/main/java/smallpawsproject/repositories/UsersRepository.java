package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smallpawsproject.model.EndUser;

public interface UsersRepository extends JpaRepository<EndUser, Integer>
{
}
