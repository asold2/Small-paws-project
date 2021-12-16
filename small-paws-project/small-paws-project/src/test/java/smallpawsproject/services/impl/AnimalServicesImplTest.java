package smallpawsproject.services.impl;

import com.google.gson.JsonArray;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smallpawsproject.SmallPawsProjectApplication;
import smallpawsproject.model.Animal;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.rmi.ClientRMIImpl;
import smallpawsproject.rmi.Server;
import smallpawsproject.services.AnimalServices;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest(classes = SmallPawsProjectApplication.class)
class AnimalServicesImplTest {

    private ClientRMI clientRMI;
    List<Animal> animals;
    AnimalServicesImpl animalServices;

    @BeforeEach
    public void setUp(){
        clientRMI = mock(ClientRMIImpl.class);
        animalServices = new AnimalServicesImpl();
        animalServices.setClient(clientRMI);
        animals = new ArrayList<>();
    }

    @Test
    void addAnimal() throws RemoteException {
        Animal animal = new Animal("Parrot", 1, "M", "Nice parrot", null, true,true , false, "healthy");
        animals.add(animal);
        animalServices.AddAnimal(animal);


        Mockito.when(clientRMI.getAnimals()).thenReturn(animals);
        JSONArray testJsonArray = animalServices.GetAnimals();
        assertEquals(testJsonArray.size(), 1);

    }

    @Test
    void updateAnimal() throws RemoteException {
        Animal existentAnimal = new Animal("Parrot", 1, "M", "Nice parrot", null, true,true , false, "healthy");
        animalServices.AddAnimal(existentAnimal);
        animals.add(existentAnimal);


        Animal updatedVersion = existentAnimal;
        updatedVersion.set(2, "F", "bad parrot", false, false, true, "mentally disturbed");

        animalServices.updateAnimal(updatedVersion);

        Mockito.when(clientRMI.getAnimals()).thenReturn(animals);
        JSONArray returnedAnimals = animalServices.GetAnimals();
        assertNotNull(animalServices.GetAnimals());

    }

    @Test
    void getAnimals() {
        Animal existentAnimal = new Animal("Parrot", 1, "M", "Nice parrot", null, true,true , false, "healthy");
        animals.add(existentAnimal);

        try {
            Mockito.when(clientRMI.getAnimals()).thenReturn(animals);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        JSONArray gottenAnimals = animalServices.GetAnimals();
        assertNotNull(animalServices.GetAnimals());
    }
}