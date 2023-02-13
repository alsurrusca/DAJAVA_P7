package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<User> users = userService.findAll();
        assertNotNull(users);


    }

    @Test
    public void saveBidTest(){
        User userLis = new User();
        userLis.setUsername("username");
        userLis.setPassword("password");
        userLis = userService.save(userLis);
        assertNotNull(userLis.getId());
    }


    @Test
    public void getByIdTest(){

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user = userService.save(user);

        // When
        Optional<User> foundBidList = userService.getById(user.getId());

        // Then
        assertTrue(foundBidList.isPresent());
        assertEquals(user.getUsername(), foundBidList.get().getUsername());

    }

    @Test
    public void testDelete() {
        // Given
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user = userService.save(user);

        // When
        userService.delete(user);

        // Then
        Optional<User> deleteUser = userService.getById(user.getId());
        assertFalse(deleteUser.isPresent());
    }
}
