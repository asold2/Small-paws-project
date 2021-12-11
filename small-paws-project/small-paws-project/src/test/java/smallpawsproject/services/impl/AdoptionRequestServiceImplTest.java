package smallpawsproject.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import smallpawsproject.SmallPawsProjectApplication;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.rmi.Server;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SmallPawsProjectApplication.class)

class AdoptionRequestServiceImplTest {

    @Spy//Spy, because we need the server, clientRMI and clientFactory to "act" as if in the normal environment
    private ClientFactory clientFactory;
    @Spy
    private ClientRMI clientRMI;
    @Spy
    private Server server;
    @Mock
    private List<AdoptionRequest> requests;
    AdoptionRequestServiceImpl adoptionRequestService;

    Animal animal;
    PetOwner petOwner;
    Veterinarian veterinarian;


    @BeforeEach
    private void setUp(){
        clientRMI = clientFactory.getClient();

        try {
            clientRMI.setServer(server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        adoptionRequestService = new AdoptionRequestServiceImpl();
        adoptionRequestService.setClient(clientRMI);
        requests = new ArrayList<>();

        animal = new Animal("Parrot", 1, "M", "Nice parrot", null, true,true , false, "healthy");
        animal.setId(5);
        petOwner = new PetOwner("Student","kamtjatka, 12",8700,  20,200000,"Single", "Andrei2","Soldan2","M",
                231102, "blablabla", "password", "asoldan2@mail.ru", "PetOwner");
        petOwner.setUserId(15);
        veterinarian = new Veterinarian("Veterinarian", "veterinarian", "veterinarian@mail.com", "Veterinarian");
        veterinarian.setUserId(1);
    }


    @Test
    void makeNewRequest() {
        AdoptionRequest adoptionRequest = new AdoptionRequest(new Date(), animal, petOwner, veterinarian, false, "animal");
        adoptionRequestService.makeNewRequest(adoptionRequest);
        requests.add(adoptionRequest);

        try {
            Mockito.when(clientRMI.getAdoptionRequests()).thenReturn(requests);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        List<AdoptionRequest> returnedRequests = adoptionRequestService.getAdoptionRequests();
        assertTrue(adoptionRequestService.getAdoptionRequests().size()==1);

    }

    @Test
    void getAdoptionRequests() {
    }

    @Test
    void updateAdoptionRequest() {
    }

    @Test
    void setClient() {
    }
}