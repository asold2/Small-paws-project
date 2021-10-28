package smallpawsproject.smallpawsproject.Repositories;

import org.springframework.data.repository.CrudRepository;
import smallpawsproject.smallpawsproject.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{

}
