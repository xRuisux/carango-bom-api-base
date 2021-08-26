package br.com.caelum.carangobom.user;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class UserMapper {
    private Long id;
    private String name;
    private String email;

    public UserMapper(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public static List<UserMapper> usersListConverters(List<User> users) {
		return users.stream().map(UserMapper::new).collect(Collectors.toList());
	}

}
