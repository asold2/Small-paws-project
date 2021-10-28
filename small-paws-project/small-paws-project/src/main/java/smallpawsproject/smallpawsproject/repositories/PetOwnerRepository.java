package smallpawsproject.smallpawsproject.repositories;

import org.springframework.data.repository.CrudRepository;
import smallpawsproject.smallpawsproject.model.PetOwner;

public interface PetOwnerRepository extends CrudRepository<PetOwner, Long>
{

}
