package smallpawsproject.services.impl;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.repositories.AdoptionRequestRepository;
import smallpawsproject.services.AdoptionRequestService;

import java.util.ArrayList;
import java.util.*;

public class AdoptionRequestServiceImpl implements AdoptionRequestService {


    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }


    @Override
    public void makeNewRequest(AdoptionRequest adoptionRequest) {
//        AdoptionRequest temp = null;
//        for(AdoptionRequest ar : adoptionRequestRepository.findAll()){
//            if(adoptionRequest.getAnimalId().getId()==ar.getAnimalId().getId() && adoptionRequest.getUserId().getUserId()==ar.getUserId().getUserId()){
//                System.out.println("Matching requests");
//                return;
//            }else{
//                temp=adoptionRequest;
//                adoptionRequestRepository.save(temp);
//                break;
//            }
//        }
        adoptionRequestRepository.save(adoptionRequest);
    }

    @Override
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    @Override
    public AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) {
        AdoptionRequest temp = adoptionRequestRepository.getById(adoptionRequest.getRequestId());
        temp.set(adoptionRequest.isApprove(), adoptionRequest.getVeterinarianId(), adoptionRequest.getDate());
        adoptionRequestRepository.save(temp);
        return temp;
    }


}
