package smallpawsproject.services.impl;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.repositories.AdoptionRequestRepository;
import smallpawsproject.services.AdoptionRequestService;
import java.util.*;

public class AdoptionRequestServiceImpl implements AdoptionRequestService {


    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }


    @Override
    public void makeNewRequest(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.save(adoptionRequest);

    }

    @Override
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    @Override
    public void updateAdoptionRequest(AdoptionRequest adoptionRequest) {
        AdoptionRequest temp = adoptionRequestRepository.getById(adoptionRequest.getRequestId());
        temp.set(adoptionRequest.isApprove(), adoptionRequest.getVeterinarianId(), adoptionRequest.getDate());
        adoptionRequestRepository.save(temp);

    }


}
