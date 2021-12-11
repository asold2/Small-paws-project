package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.services.AdoptionRequestService;
import smallpawsproject.services.PetOwnerService;
import smallpawsproject.services.UsersService;

import java.util.*;

@RestController
public class AdoptionRequestController {

    private  final AdoptionRequestService adoptionRequestService;
    private final UsersService usersService;
    private final PetOwnerService petOwnerService;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService, UsersService usersService, PetOwnerService petOwnerService) {
        this.adoptionRequestService = adoptionRequestService;
        this.usersService = usersService;
        this.petOwnerService = petOwnerService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/newRequest")
    public void makeNewRequest( @RequestBody AdoptionRequest adoptionRequest){
        System.out.println(adoptionRequest.getUserId() + "!!!!!!!!");

        var temp = new AdoptionRequest(adoptionRequest.getDate(), adoptionRequest.getAnimalId(), adoptionRequest.getUserId(),  null, false, adoptionRequest.getAnimalName());
        System.out.println(temp);
        adoptionRequestService.makeNewRequest(temp);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/requests")
    @ResponseBody
    public List<AdoptionRequest> getAdoptionRequests(){
        return adoptionRequestService.getAdoptionRequests();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}" )
    @ResponseBody
    public PetOwner getPetOwnerById(@PathVariable int id)
    {
        return petOwnerService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/veterinarian/{id}" )
    @ResponseBody
    public Veterinarian getVeterinarianById(@PathVariable int id)
    {
        return usersService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/request_decision")
    @ResponseBody
    public void updateAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest){
        adoptionRequestService.updateAdoptionRequest(adoptionRequest);
    }

}
