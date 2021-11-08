package Small.Paws.smallpawsdata.repositories;

import Small.Paws.smallpawsdata.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long>
{
}
