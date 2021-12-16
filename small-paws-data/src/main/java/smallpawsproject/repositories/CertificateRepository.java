package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smallpawsproject.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
}
