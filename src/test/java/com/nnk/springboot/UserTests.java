package com.nnk.springboot;


import com.nnk.springboot.domain.User;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

public class UserTests {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Test
        public void userListTest() {
            User user = new User("username", "password", "fullname","role");

            // Save
            user = userRepository.save(user);
            Assert.assertNotNull(user.getId());
            Assert.assertEquals(user.getUsername(), "username", "username");

            // Update
            user.setUsername("userName");
            user = userRepository.save(user);
            Assert.assertEquals(user.getUsername(), "userName", "userName");

            // Find
            List<User> listUser = userRepository.findAll();
            Assert.assertTrue(listUser.size() > 0);

            // Delete
            Integer id = user.getId();
            userRepository.delete(user);
            Optional<User> userList = userRepository.findById(id);
            Assert.assertFalse(userList.isPresent());
        }



}




