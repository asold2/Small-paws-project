package smallpawsproject.services.impl;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.repositories.AdoptionRequestRepository;
import smallpawsproject.services.AdoptionRequestService;
import java.util.*;

public class AdoptionRequestServiceImpl implements AdoptionRequestService {


    private final AdoptionRequestRepository adoptionRequestRepository;

    /**
     * @param adoptionRequestRepository
     * The constructor is used to initialize the repository.
     */
    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    /**
     * This method is saving the request that was made for an adoption in the database.
     * @param adoptionRequest
     */
    @Override
    public void makeNewRequest(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.save(adoptionRequest);

    }

    @Override
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    /**
     * This method is setting the request that has to be updated to the new values that were sent from the user.
     * @param adoptionRequest
     */
    @Override
    public void updateAdoptionRequest(AdoptionRequest adoptionRequest) {
        AdoptionRequest temp = adoptionRequestRepository.getById(adoptionRequest.getRequestId());
        temp.set(adoptionRequest.isApprove(), adoptionRequest.getVeterinarianId(), adoptionRequest.getDate());
        adoptionRequestRepository.save(temp);

    }


}
