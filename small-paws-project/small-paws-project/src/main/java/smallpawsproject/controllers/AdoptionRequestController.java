package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.services.AdoptionRequestService;
import smallpawsproject.services.AnimalServices;
import smallpawsproject.services.PetOwnerService;
import smallpawsproject.services.UsersService;

import java.util.*;

@RestController
public class AdoptionRequestController {

    private  final AdoptionRequestService adoptionRequestService;
    private final AnimalServices animalServices;
    private final UsersService usersService;
    private final PetOwnerService petOwnerService;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService, AnimalServices animalServices, UsersService usersService, PetOwnerService petOwnerService) {
        this.adoptionRequestService = adoptionRequestService;
        this.animalServices = animalServices;
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
//        System.out.println(adoptionRequestService.getAdoptionRequests().get(0).getRequestId() + "Heeeeeeereeeeeee");

        return adoptionRequestService.getAdoptionRequests();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}" )
    @ResponseBody
    public PetOwner getPetOwnerById(@PathVariable int id)
    {
//        System.out.println(petOwnerService.getUserById(id).getMyRequests());
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
    public void updateAdoptionrequest(@RequestBody AdoptionRequest adoptionRequest){
        System.out.println(adoptionRequest.getVeterinarianId()+"<<<<<<<<<<<<");
//        System.out.println(adoptionRequest.getRequestId()+"<<<<<<<<<<<<");
        System.out.println(adoptionRequest.getAnimalName()+"<<<<<<<<<<<<");


//        var temp = new AdoptionRequest(adoptionRequest.getRequestId(), adoptionRequest.getDate(), adoptionRequest.getAnimalId(), adoptionRequest.getUserId(), adoptionRequest.getVeterinarianId(), adoptionRequest.isApprove(), adoptionRequest.getAnimalName());

        adoptionRequestService.updateAdoptionRequest(adoptionRequest);

    }

}
