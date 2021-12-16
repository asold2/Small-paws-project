package smallpawsproject.services.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smallpawsproject.SmallPawsProjectApplication;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.rmi.ClientRMIImpl;
import smallpawsproject.rmi.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest(classes = SmallPawsProjectApplication.class)
class PetOwnerServiceImplTest {


    private ClientRMI clientRMI;

    PetOwnerServiceImpl petOwnerService;

    private List<PetOwner> petOwnerList;

    @BeforeEach
    public void setUp(){
        clientRMI = mock(ClientRMIImpl.class);
        petOwnerService = new PetOwnerServiceImpl();
        petOwnerService.setClient(clientRMI);
        petOwnerList = new ArrayList<>();
    }



    @Test
    void registerPetOwnerWorksCorrectly()  {
        PetOwner petOwnerTest = new PetOwner("Student","kamtjatka, 12",8700,  20,200000,"Single", "Andrei2","Soldan2","M",
        231102, "blablabla", "password", "asoldan2@mail.ru", "PetOwner");

        assertEquals(201, petOwnerService.registerPetOwner(petOwnerTest));

    }
    @Test
    void registerPetOwnerWhenEmailExists() {
        PetOwner petOwnerTest = new PetOwner("Student","kamtjatka, 12",8700,  20,200000,"Single", "Andrei2","Soldan2","M",
                231102, "blablabla", "password", "asoldan@mail.ru", "PetOwner");
        assertEquals(409, petOwnerService.registerPetOwner(petOwnerTest));

    }
    @Test
    void registerPetOwnerUsernameExists()  {
        PetOwner petOwnerTest = new PetOwner("Student","kamtjatka, 12",8700,  20,200000,"Single", "Andrei2","Soldan2","M",
                231102, "andrei", "password", "asoldan2@mail.ru", "PetOwner");
        assertEquals(409, petOwnerService.registerPetOwner(petOwnerTest));
    }
    @Test
    void registerPetOwnerWhenFirstNameAndLastNameExist() throws RemoteException {
        PetOwner petOwnerTest = new PetOwner("Student","kamtjatka, 12",8700,  20,200000,"Single", "Andrei","Soldan","M",
                231102, "andrei232323", "password", "asoldan2@mail.ru", "PetOwner");
        assertEquals(409, petOwnerService.registerPetOwner(petOwnerTest));
    }

    @Test
    void checkUsername_UsernameExists() {
        int answer = 0;
        answer = petOwnerService.checkUsername("andrei");
        assertEquals(HttpServletResponse.SC_FORBIDDEN, answer);
    }
    @Test
    void checkUsername_UsernameDoesNotExist() {
        int answer = 0;
        answer = petOwnerService.checkUsername("deff-non-existent-user-name");
        assertEquals(HttpServletResponse.SC_ACCEPTED, answer);
    }

    @Test
    void getUserByIdExistentUser() {
        PetOwner petOwnerTest = new PetOwner("Student","Kamtjatka, 12",8700,  20,200000,"Single", "Andrei","Soldan","M",
                231101, "andrei", "pass", "asoldan2@mail.ru", "PetOwner");
        petOwnerTest.setUserId(15);
        List<PetOwner> users = new ArrayList<>();
        users.add(petOwnerTest);

        try {
            Mockito.when(clientRMI.getPetOwners()).thenReturn(users);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        List<PetOwner> gottenUsers = petOwnerService.getPetOwners();
        assertEquals(petOwnerTest.getUserName(), petOwnerService.getUserById(petOwnerTest.getUserId()).getUserName());
        assertEquals(petOwnerTest.getFirstName(), petOwnerService.getUserById(petOwnerTest.getUserId()).getFirstName());
        assertEquals(petOwnerTest.getJobTitle(), petOwnerService.getUserById(petOwnerTest.getUserId()).getJobTitle());
    }
}