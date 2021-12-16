package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.rmi.ClientRMI;

import java.util.List;

@Service
public interface AdoptionRequestService {
    void makeNewRequest(AdoptionRequest adoptionRequest);
    List<AdoptionRequest> getAdoptionRequests();
    void updateAdoptionRequest(AdoptionRequest adoptionRequest);
    void setClient(ClientRMI clientRMI); // method used to inject the client into the service for testing

}
