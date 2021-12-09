package smallpawsproject.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smallpawsproject.SmallPawsDataApplication;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmallPawsDataApplication.class)
public class PetOwnerTesting {
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Test
    public void whenSaveAndRetrievePetOwner_thenOK()
    {
        PetOwner petOwner = new PetOwner("student", "Hoarseness", 2355,
                25, 65555, "single", "Chris", "White", "M", 12,
                "Whitos", "password123", "WhiteChris@gmail.com", "PetOwner");
        List<PetOwner> petOwners = petOwnerRepository.findAll();

        for (var temp: petOwners) {
            if (temp.getId().equals(petOwner.getId()))
                petOwner.setUserId(temp.getUserId());
        }
        PetOwner foundPetOwner = petOwnerRepository.findById(petOwner.getUserId()).orElse(null);
        if (foundPetOwner == null)
        {
            petOwnerRepository.save(petOwner);
        }
        assertNotNull(foundPetOwner);
        assertEquals(petOwner.getId(), foundPetOwner.getId());

        //deleting saved pet owner from database
        petOwnerRepository.deleteById(foundPetOwner.getUserId());
    }
}
