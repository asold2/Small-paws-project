package Small.Paws.smallpawsdata.repositories;

import Small.Paws.smallpawsdata.model.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long>
{}
