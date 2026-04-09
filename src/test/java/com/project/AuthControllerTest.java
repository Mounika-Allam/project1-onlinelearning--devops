package com.project;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.controller.AuthController;
import com.project.model.User;
import com.project.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthController authController;

    @Test
    void testRegisterPage() {
        String view = authController.registerPage();
        assertThat(view).isEqualTo("register");
    }

    @Test
    void testLoginPage() {
        String view = authController.loginPage();
        assertThat(view).isEqualTo("login");
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(encoder.encode("password")).thenReturn("encodedPassword");

        String result = authController.register(user);

        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getRole()).isEqualTo("USER");

        verify(repo, times(1)).save(user);
        assertThat(result).isEqualTo("redirect:/login");
    }
}
