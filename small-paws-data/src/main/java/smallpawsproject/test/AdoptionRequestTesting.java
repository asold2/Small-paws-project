package smallpawsproject.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smallpawsproject.SmallPawsDataApplication;
import smallpawsproject.model.*;
import smallpawsproject.repositories.AdoptionRequestRepository;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.repositories.UsersRepository;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmallPawsDataApplication.class)
public class AdoptionRequestTesting {

    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void WhenSaveAndRetrieveAdoptRequest_thenOK()
    {
        Animal animal = animalRepository.save(new Animal("bird", 3, "Tropical bird from South America.", new byte[30], true, false, false, "healhy"));
        PetOwner petOwner = petOwnerRepository.save(new PetOwner("student", "Hoarseness", 2355,
                25, 65555, "single", "Chris", "White", "M", 12,
                "Whitos", "password123", "WhiteChris@gmail.com", "PetOwner"));
        List<EndUser> users = usersRepository.findAll();
        Veterinarian veterinarian = new Veterinarian();
        for (var user:users) {
            if (user.getRole().equals("Veterinarian"))
            {
                veterinarian = (Veterinarian) user;
            }
        }
        AdoptionRequest adoptionRequest = adoptionRequestRepository.save(new AdoptionRequest(new Date(), animal, petOwner, veterinarian, false, "Cookie"));
        AdoptionRequest foundAdoptionRequest = adoptionRequestRepository.findById(adoptionRequest.getRequestId()).orElse(null);
        assertNotNull(foundAdoptionRequest);
        assertEquals(adoptionRequest.getRequestId(), foundAdoptionRequest.getRequestId());

        if (foundAdoptionRequest != null)
        {
            animalRepository.deleteById(animal.getId());
        }
        petOwnerRepository.deleteById(petOwner.getUserId());
    }

    @Test
    public void UpdateAdoptionRequest()
    {
        Animal animal = animalRepository.save(new Animal("bird", 3, "Tropical bird from South America.", new byte[30], true, false, false, "healhy"));
        PetOwner petOwner = petOwnerRepository.save(new PetOwner("student", "Hoarseness", 2355,
                25, 65555, "single", "Chris", "White", "M", 12,
                "Whitos", "password123", "WhiteChris@gmail.com", "PetOwner"));
        List<EndUser> users = usersRepository.findAll();
        Veterinarian veterinarian = new Veterinarian();
        for (var user:users) {
            if (user.getRole().equals("Veterinarian"))
            {
                veterinarian = (Veterinarian) user;
            }
        }
        AdoptionRequest adoptionRequest = adoptionRequestRepository.save(new AdoptionRequest(new Date(), animal, petOwner, veterinarian, false, "Cookie"));

        //updating request status
        adoptionRequest.setApprove(true);
        adoptionRequestRepository.save(adoptionRequest);
        AdoptionRequest foundAdoptionRequest = adoptionRequestRepository.findById(adoptionRequest.getRequestId()).orElse(null);
        assertEquals(foundAdoptionRequest.isApprove(), true);

        if (foundAdoptionRequest != null)
        {
            animalRepository.deleteById(animal.getId());
        }
        petOwnerRepository.deleteById(petOwner.getUserId());
    }
}