package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.AdoptionRequestService;
import smallpawsproject.services.AnimalServices;
import smallpawsproject.services.PetOwnerService;
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
    @Autowired
    private final PetOwnerService petOwnerService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService, AnimalServices animalServices, UsersService usersService, PetOwnerService petOwnerService) {
        this.adoptionRequestService = adoptionRequestService;
        this.animalServices = animalServices;
        this.usersService = usersService;
        this.petOwnerService = petOwnerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newRequest")
    public void makeNewRequest( @RequestBody AdoptionRequest adoptionRequest){
        System.out.println(adoptionRequest.getUserId().getId() + "!!!!!!");
        PetOwner tem = getPetOwnerById(adoptionRequest.getUserId().getId());
        System.out.println(tem.getUserName());
        AdoptionRequest temp = new AdoptionRequest( adoptionRequest.getDate(), adoptionRequest.getAnimalId(), tem,  adoptionRequest.getVeterinarianId(), false, adoptionRequest.getAnimalName());
//        System.out.println(temp.getUserId().getUserId() + "!!!! " + temp.getRequestId() + " " + temp.getAnimalId());

        adoptionRequestService.makeNewRequest(temp);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/requests")
    @ResponseBody
    public List<AdoptionRequest> getAdoptionrequests(){
        return adoptionRequestService.getAdoptionRequests();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}" )
    @ResponseBody
    public PetOwner getPetOwnerById(@PathVariable int id)
    {
        return petOwnerService.getUserById(id);
    }

}
