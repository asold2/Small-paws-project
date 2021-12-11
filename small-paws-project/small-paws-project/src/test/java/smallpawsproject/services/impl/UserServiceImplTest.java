package smallpawsproject.services.impl;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smallpawsproject.SmallPawsProjectApplication;
import smallpawsproject.model.Animal;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.rmi.ClientRMIImpl;
import smallpawsproject.rmi.Server;
import smallpawsproject.services.UsersService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SmallPawsProjectApplication.class)

public class UserServiceImplTest {

    @Spy//Spy, because we need the clientRMI and clientFactory to "act" as if in the normal environment
    private ClientFactory clientFactory;
    @Spy
    private ClientRMI clientRMI;
    @Spy
    private Server server;
    UserServiceImpl usersService;

    @BeforeEach
    public void setUp(){
        clientRMI = clientFactory.getClient();
        try {
            clientRMI.setServer(server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        usersService = new UserServiceImpl();
        usersService.setClient(clientRMI);
    }

    @Test
    void getUsersWhenOk() throws RemoteException {
        EndUser testUser = new EndUser("Vet", "vet", "vet@mail.com", "Veterinarian");
        List<EndUser> users = new ArrayList<>();
        users.add(testUser);
        Mockito.when(clientRMI.getUsers()).thenReturn(users);//mocking the clientRMI to get the list of users from a mocked Server
        List<EndUser> gottenUsers = usersService.getUsers();
        assertTrue(usersService.getUsers().size()>0);
    }
    @Test
    void getUsersWhenNoUsersInDb() throws RemoteException {
        List<EndUser> users = new ArrayList<>();
        Mockito.when(clientRMI.getUsers()).thenReturn(users);//mocking the clientRMI to get the list of users from a mocked Server
        List<EndUser> gottenUsers = usersService.getUsers();
        assertEquals(0, usersService.getUsers().size());
    }

    @Test
    void checkWhenExistentUser() {

        EndUser testUser = new EndUser("Veterinarian", "veterinarian", "veterinarian@gmail.com", "Veterinarian");
        usersService.getUsers().add(testUser);

        assertNotNull(usersService.check(testUser.getUserName(), testUser.getPassword()));
    }

    @Test
    void checkWhenNonExistentUser() {

        EndUser testUser = new EndUser("Wrong", "EvenWronger", "wrong@gmail.com", "Veterinarian");

        assertNull(usersService.check(testUser.getUserName(), testUser.getPassword()).getUserName());
        assertNull(usersService.check(testUser.getUserName(), testUser.getPassword()).getPassword());
        assertNull(usersService.check(testUser.getUserName(), testUser.getPassword()).getUserId());
    }

    @Test
    void getUserById() {
        Veterinarian testUser = new Veterinarian("Veterinarian", "veterinarian", "veterinarian@gmail.com", "Veterinarian");
        testUser.setUserId(1);
//        Mockito.when(usersService.getUserById(testUser.getUserId())).thenReturn(testUser);
        assertEquals(testUser.getUserName(), usersService.getUserById(testUser.getUserId()).getUserName());
        assertEquals(testUser.getPassword(), usersService.getUserById(testUser.getUserId()).getPassword());
        assertEquals(testUser.getRole(), usersService.getUserById(testUser.getUserId()).getRole());
    }

    @Test
    void getUserByIdNoSuchId() {
        Veterinarian testUser = new Veterinarian("Veterinarian", "veterinarian", "veterinarian@gmail.com", "Veterinarian");
        testUser.setUserId(1233456);
//        Mockito.when(usersService.getUserById(testUser.getUserId())).thenReturn(testUser);
        assertNotEquals(testUser.getUserName(), usersService.getUserById(testUser.getUserId()).getUserName());
        assertNotEquals(testUser.getPassword(), usersService.getUserById(testUser.getUserId()).getPassword());
        assertNotEquals(testUser.getRole(), usersService.getUserById(testUser.getUserId()).getRole());
    }
}