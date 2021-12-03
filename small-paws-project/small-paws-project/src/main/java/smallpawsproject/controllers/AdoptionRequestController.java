package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.services.AdoptionRequestService;
import smallpawsproject.services.AnimalServices;
import smallpawsproject.services.UsersService;

import java.util.*;

@RestController
public class AdoptionRequestController {

    @Autowired
    private final AdoptionRequestService adoptionRequestService;
    @Autowired
    private final AnimalServices animalServices;
    @Autowired
    private final UsersService usersService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService, AnimalServices animalServices, UsersService usersService) {
        this.adoptionRequestService = adoptionRequestService;
        this.animalServices = animalServices;
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newRequest")
    public void makeNewRequest( @RequestBody AdoptionRequest adoptionRequest){
        AdoptionRequest temp = new AdoptionRequest( adoptionRequest.getDate(), adoptionRequest.getAnimalId(), adoptionRequest.getUserId(),  adoptionRequest.getVeterinarianId(), false);
        System.out.println(temp.getUserId() + " " + temp.getRequestid() + " " + temp.getAnimalId());

        adoptionRequestService.makeNewRequest(temp);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/requests")
    @ResponseBody
    public List<AdoptionRequest> getAdoptionrequests(){
        return adoptionRequestService.getAdoptionRequests();
    }

}
