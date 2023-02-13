package com.nnk.springboot.ServiceTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CustomUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User user;

    @Before
    public void setup() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
    }


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_whenUserNotExist_shouldThrowUsernameNotFoundException() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        customUserDetailsService.loadUserByUsername("testuser");
    }

    @Test
    public void loadUserByUsername_ReturnsUserDetails_WhenUserExists() {
        // Given
        String username = "test-user";
        User user = new User(username, "password");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Then
        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getPassword()).isEqualTo("password");
        assertThat(userDetails.getAuthorities()).isEmpty();
    }

    @Test
    public void loadUserByUsername_ThrowsUsernameNotFoundException_WhenUserDoesNotExist() {
        // Given
        String username = "nonexistent-user";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> customUserDetailsService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found");
    }

}
