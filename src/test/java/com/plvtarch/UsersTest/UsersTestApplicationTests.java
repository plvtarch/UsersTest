package com.plvtarch.UsersTest;

import com.plvtarch.UsersTest.entity.User;
import com.plvtarch.UsersTest.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class UsersTestApplicationTests {

	@Autowired
	private UsersRepository usersRepository;

	@Test
	@Rollback(value = false)
	public void testCreateUser()
	{
		User user = new User("Andrey", "Andrey777", "qwerty123"
				, "andrey777@aox.com", LocalDate.of(2021, 6,12));
		User savedUser = usersRepository.save(user);
		assertNotNull(savedUser);
	}

	@Test
	public void testFindUserByName() {
		List<User> user = usersRepository.findByName("Andrey");
		assertNotNull(user.stream().findFirst());
	}

}
