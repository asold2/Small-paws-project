package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smallpawsproject.model.UserLogIn;
@Repository
public interface UserLogInRepository extends JpaRepository<UserLogIn, Long>
{

}
