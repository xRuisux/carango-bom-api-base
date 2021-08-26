package br.com.caelum.carangobom.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javassist.NotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    private List<User> expectedUsers = List.of(
        new User(1L, "Admin", "admin@email.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq"),
        new User(2L, "Admin", "admin1@email.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq")
    );


    @Test
    void shouldReturnAllUsers() {
        
    
        List<User> usersOnDatabase = userService.findAllByOrderById(); 

        assertEquals(expectedUsers.size(), usersOnDatabase.size());
        assertEquals(expectedUsers.get(0).getId(), usersOnDatabase.get(0).getId());
        assertEquals(expectedUsers.get(1).getId(), usersOnDatabase.get(1).getId());
        assertEquals(expectedUsers.get(0).getEmail(), usersOnDatabase.get(0).getEmail());
        assertEquals(expectedUsers.get(1).getEmail(), usersOnDatabase.get(1).getEmail());
        assertEquals(expectedUsers.get(0).getName(), usersOnDatabase.get(0).getName());
        assertEquals(expectedUsers.get(1).getName(), usersOnDatabase.get(1).getName());
        assertEquals(expectedUsers.get(0).getPassword(), usersOnDatabase.get(0).getPassword());
        assertEquals(expectedUsers.get(1).getPassword(), usersOnDatabase.get(1).getPassword());

    }

    @Test
    void shouldReturnUserByIdWhenItExists() throws Exception {
    
        User userOnDatabase = userService.findById(1L); 

        assertEquals(expectedUsers.get(0).getId(), userOnDatabase.getId());
        assertEquals(expectedUsers.get(0).getEmail(), userOnDatabase.getEmail());
        assertEquals(expectedUsers.get(0).getName(), userOnDatabase.getName());
        assertEquals(expectedUsers.get(0).getPassword(), userOnDatabase.getPassword());

    }

    @Test
    void shouldReturnNotFoundExceptionWhenNotExists() throws Exception {
        assertThrows(NotFoundException.class,  () -> {
            userService.findById(4L);
        });
    }

}
