package smallpawsproject.services;

import smallpawsproject.model.AdoptionRequest;
import java.util.*;

public interface AdoptionRequestService {
    void makeNewRequest(AdoptionRequest adoptionRequest);
    List<AdoptionRequest> getAdoptionRequests();
    void updateAdoptionRequest(AdoptionRequest adoptionRequest);
}
