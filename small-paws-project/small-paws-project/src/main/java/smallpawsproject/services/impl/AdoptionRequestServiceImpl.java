package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.AdoptionRequestService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService {

    @Autowired
    private  ClientFactory clientFactory;

    private  ClientRMI client;
    private ArrayList<AdoptionRequest> existentRequests;

    public AdoptionRequestServiceImpl() {
        clientFactory = new ClientFactory();

        client = clientFactory.getClient();

        try {
            client.connect();
        }catch (RemoteException e)
        {
            e.printStackTrace();
        }


        try {
            existentRequests = (ArrayList<AdoptionRequest>) client.getAdoptionRequests();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        System.out.println(existentRequests.get(0).getAnimalId());
    }





    @Override
    public void makeNewRequest(AdoptionRequest adoptionRequest) {
        System.out.println(adoptionRequest.getAnimalId().getId() + " this animal id");
        boolean makeOrNotMake = true;
        for(int i=0; i<existentRequests.size(); i++){
            System.out.println(existentRequests.get(i).getAnimalId().getId() + "All the animals that have been requested!!!");
            if(adoptionRequest.getUserId().getUserId().equals(existentRequests.get(i).getUserId().getUserId())
                    && adoptionRequest.getAnimalId().getId().equals(existentRequests.get(i).getAnimalId().getId())
            ){
                if( adoptionRequest.getAnimalId().getId().equals(existentRequests.get(i).getAnimalId().getId())){
                    System.out.println("There is such a request already");
                  makeOrNotMake = false;
                  break;
                }
            }
        }
        if(makeOrNotMake){
            System.out.println(adoptionRequest.getAnimalId());
            try {
                client.makeNewRequest(adoptionRequest);
                existentRequests.add(adoptionRequest);
                System.out.println("Request saved");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<AdoptionRequest> getAdoptionRequests() {
        List<AdoptionRequest> requests = new ArrayList<>();
        try {
            System.out.println("Adoption Requests from client in service");
            requests = client.getAdoptionRequests();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) {
        try {
            return client.updateAdoptionRequest(adoptionRequest);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
