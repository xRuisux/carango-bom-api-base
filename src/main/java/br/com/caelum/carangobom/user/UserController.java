package br.com.caelum.carangobom.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserMapper>> list() {
        
        List<User> users = this.userService.findAllByOrderById();
        return ResponseEntity.ok(UserMapper.usersListConverters(users));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserMapper> delete(@PathVariable Long id) throws NotFoundException{
        
        User user = this.userService.deleteById(id);
        return ResponseEntity.ok(new UserMapper(user));

    }
       
}
