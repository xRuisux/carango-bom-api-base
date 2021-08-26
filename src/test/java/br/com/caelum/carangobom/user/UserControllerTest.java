package br.com.caelum.carangobom.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javassist.NotFoundException;

@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    UserController userController;
    

    private List<UserMapper> expectedUsersMappers = List.of(
        new UserMapper(new User(1L, "Admin", "admin@email.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq")),
        new UserMapper(new User(2L, "Admin", "admin1@email.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq")),
        new UserMapper(new User(3L, "Admin", "admin2@email.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq"))
    );

    @Test
    void shouldReturnAllUsers() throws Exception {

        ResponseEntity<List<UserMapper>> response = userController.list(); 
        assertEquals(expectedUsersMappers.size(), response.getBody().size());
    }

    @Test
    void shouldDeleteUserWhenItExists() throws Exception {

        ResponseEntity<UserMapper> response = userController.delete(3l); 
        assertEquals(expectedUsersMappers.get(2).getId(), response.getBody().getId());
        assertEquals(expectedUsersMappers.get(2).getEmail(), response.getBody().getEmail());
        assertEquals(expectedUsersMappers.get(2).getName(), response.getBody().getName());
        assertThrows(NotFoundException.class,  () -> {
            userController.delete(3L);
        });
    }
}
