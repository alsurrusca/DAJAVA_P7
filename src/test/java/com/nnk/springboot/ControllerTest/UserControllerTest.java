package com.nnk.springboot.ControllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;


    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
    }


    @Test
    public void homeTest() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);
        Model model = mock(Model.class);
        //when(userRepository.findAll()).thenReturn(users);

        // Act
        userController.home(model);

        // Assert
        verify(model).addAttribute("users", users);
    }

    @Test
    public void addUserTest() {
        // Arrange
        UserController controller = new UserController();
        User user = new User();

        // Act
        String result = controller.addUser(user);

        // Assert
        assertEquals("user/add", result);
    }

    @Test
    public void validateUserTest() {
        // Arrange
        User user = new User();
        user.setUsername("testname");
        user.setPassword("testpassword");
        BindingResult result = mock(BindingResult.class);
        Model model = mock(Model.class);
        when(result.hasErrors()).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        String view = userController.validate(user, result, model);

        // Assert
        assertEquals("redirect:/user/list", view);
        verify(userRepository).save(user);
    }


        @Test
        public void showUpdateFormTest() {
// Arrange
            User user = new User();
            user.setId(1);
            user.setUsername("testname");
            user.setPassword("testpassword");
            when(userRepository.findById(1)).thenReturn(Optional.of(user));
            Model model = mock(Model.class);

        // Act
            String result = userController.showUpdateForm(1, model);

        // Assert
            assertEquals("user/update", result);
            verify(model).addAttribute("user", user);
    }

    @Test
    public void validateSuccess() {
        User user = new User();
        user.setPassword("password");
        when(result.hasErrors()).thenReturn(false);
        String view = userController.validate(user, result, model);
        assertEquals("redirect:/user/list", view);
    }

    @Test
    public void validateError() {
        User user = new User();
        user.setPassword("password");
        when(result.hasErrors()).thenReturn(true);
        String view = userController.validate(user, result, model);
        assertEquals("user/add", view);
    }

    @Test
    public void updateUserTest() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        when(userRepository.save(user)).thenReturn(user);
        Model model = mock(Model.class);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // Act
        String resultString = userController.updateUser(1, user, result, model);

        // Assert
        assertEquals("redirect:/user/list", resultString);
        verify(userRepository).save(user);
        verify(model).addAttribute("users", userRepository.findAll());
    }

    @Test
    public void deleteUserTest() {
        // Arrange
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Model model = mock(Model.class);

        // Act
        userController.deleteUser(1, model);

        // Assert
        verify(userRepository).delete(user);
        verify(model).addAttribute("users", userRepository.findAll());    }

}
