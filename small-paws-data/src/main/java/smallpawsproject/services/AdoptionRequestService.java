package smallpawsproject.services;

import smallpawsproject.model.AdoptionRequest;


import java.util.ArrayList;
import java.util.*;

public interface AdoptionRequestService {
    void makeNewRequest(AdoptionRequest adoptionRequest);
    List<AdoptionRequest> getAdoptionRequests();
    AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest);
}
