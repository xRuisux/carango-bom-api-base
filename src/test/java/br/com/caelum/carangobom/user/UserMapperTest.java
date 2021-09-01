package br.com.caelum.carangobom.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserMapperTest {
    @Test
	void mustReturnUserMapperList() {
		List<User> usersList = List.of(
				new User(1L, "adm", "adm@gmail.com", "123456"),
				new User(2L, "adm", "adm1@gmail.com", "123456")
			);	
		List<UserMapper> userMapperList = UserMapper.usersListConverters(usersList);
		assertEquals(2, userMapperList.size());
	}

}
