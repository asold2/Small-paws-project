package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smallpawsproject.model.Animal;
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>
{
}
