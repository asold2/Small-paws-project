package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.Certificate;

import java.util.ArrayList;
import java.util.List;
@Service
public interface CertificateService {
    void addCertificate(Certificate certificate);
    List<Certificate> getCertificatesByUserId(int id);

}
