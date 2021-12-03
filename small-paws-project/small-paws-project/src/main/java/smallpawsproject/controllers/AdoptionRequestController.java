package smallpawsproject.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.services.AdoptionRequestService;
import java.util.*;

@RestController
public class AdoptionRequestController {

    @Autowired
    private final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newRequest")
    public void makeNewRequest( @RequestBody AdoptionRequest adoptionRequest){
        AdoptionRequest temp = new AdoptionRequest( adoptionRequest.getDate(),(Integer) adoptionRequest.getAnimalId(), (Integer) adoptionRequest.getUserId(),(Integer) adoptionRequest.getVeterinarianId(), false);
        System.out.println(temp.getUserId() + " " + temp.getRequestid() + " " + temp.getAnimalId());

        adoptionRequestService.makeNewRequest(temp);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/requests")
    @ResponseBody
    public List<AdoptionRequest> getAdoptionrequests(){
        return adoptionRequestService.getAdoptionRequests();
    }

}
