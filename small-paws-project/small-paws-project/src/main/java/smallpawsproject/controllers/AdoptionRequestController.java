package smallpawsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Certificate;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.services.AdoptionRequestService;
import smallpawsproject.services.CertificateService;
import smallpawsproject.services.PetOwnerService;
import smallpawsproject.services.UsersService;

import java.util.*;

@RestController
public class AdoptionRequestController {

    private  final AdoptionRequestService adoptionRequestService;
    private final UsersService usersService;
    private final PetOwnerService petOwnerService;
    private final CertificateService certificateService;

    /**
     * AdoptionRequestController constructor
     * @param adoptionRequestService is object of AdoptionRequestService
     * @param usersService is object of UsersService
     * @param petOwnerService is object of PetOwnerService
     * @param certificateService is object of CertificateService
     */

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService, UsersService usersService, PetOwnerService petOwnerService, CertificateService certificateService) {
        this.adoptionRequestService = adoptionRequestService;
        this.usersService = usersService;
        this.petOwnerService = petOwnerService;
        this.certificateService = certificateService;
    }

    /**
     * Method for making new AdoptionRequest object
     * @param adoptionRequest is object of new AdoptionRequest with all its information
     */

    @RequestMapping(method = RequestMethod.POST, value = "/newRequest")
    public void makeNewRequest( @RequestBody AdoptionRequest adoptionRequest){

        var temp = new AdoptionRequest(adoptionRequest.getDate(), adoptionRequest.getAnimalId(), adoptionRequest.getUserId(),  null, false, adoptionRequest.getAnimalName());
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

    /**
     * This method updates information about the state of the request (approved,denied)
     * @param adoptionRequest is object of AdoptionRequest
     */

    @RequestMapping(method = RequestMethod.PATCH, value="/request_decision")
    @ResponseBody
    public void updateAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest){
        if(adoptionRequest.isApprove()){
            certificateService.addCertificate(new Certificate(adoptionRequest, null));
        }


        adoptionRequestService.updateAdoptionRequest(adoptionRequest);
    }

}
