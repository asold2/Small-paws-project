package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smallpawsproject.model.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Integer> {
}
