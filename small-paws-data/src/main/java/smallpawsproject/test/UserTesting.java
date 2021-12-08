package smallpawsproject.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smallpawsproject.SmallPawsDataApplication;
import smallpawsproject.model.EndUser;
import smallpawsproject.repositories.UsersRepository;


import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmallPawsDataApplication.class)
public class UserTesting {
    @Autowired
    private UsersRepository userRepository;

    @Test
    public void Retrieveusers()
    {
        List<EndUser> users = userRepository.findAll();

        assertNotNull(users);
    }
}
