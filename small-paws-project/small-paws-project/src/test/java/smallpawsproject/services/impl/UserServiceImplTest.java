package smallpawsproject.services.impl;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smallpawsproject.SmallPawsProjectApplication;
import smallpawsproject.model.EndUser;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.services.UsersService;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SmallPawsProjectApplication.class)

public class UserServiceImplTest {

    @MockBean
    private ClientFactory clientFactory;

    @InjectMocks
    private UsersService usersService;

    @Test
    void getUsers() {
//        Mockito.when(usersService.getUsers()).thenReturn()
        List<EndUser> users = usersService.getUsers();
        assertNotNull(users);
    }

    @Test
    void check() {
    }

    @Test
    void getUserById() {
    }
}