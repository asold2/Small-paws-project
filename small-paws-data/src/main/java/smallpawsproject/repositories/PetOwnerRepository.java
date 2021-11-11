package smallpawsproject.repositories;

import smallpawsproject.model.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long>
{}
