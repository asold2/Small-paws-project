package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.AdoptionRequest;

import java.util.List;
@Service
public interface AdoptionRequestService {
    void makeNewRequest(AdoptionRequest adoptionRequest);
    List<AdoptionRequest> getAdoptionRequests();
    AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest);
}
