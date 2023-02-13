package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class UserDomainTest {

    @Test
    public void UserTest(){
        User user = new User();

        user.setUsername("username");
        user.setPassword("password");
        user.setId(1);
        user.setFullname("fullname");
        user.setRole("Admin");

        assertEquals("username",user.getUsername());
        assertEquals("password",user.getPassword());
        assertEquals("fullname",user.getFullname());
        assertEquals("Admin",user.getRole());
        assertEquals(java.util.Optional.of(1),java.util.Optional.of(user.getId()));


    }

    @Test
    public void UserConstructorTest() {
        String username = "username";
        String password = "password";

        User user = new User (username, password);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }
}
