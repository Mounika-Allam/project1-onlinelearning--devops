package com.project;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.service.CustomUserDetailsService;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    void testLoadUserByUsernameSuccess() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("encodedPass");
        user.setRole("USER");

        when(repo.findByUsername("john")).thenReturn(user);

        UserDetails details = service.loadUserByUsername("john");

        assertThat(details.getUsername()).isEqualTo("john");
        assertThat(details.getPassword()).isEqualTo("encodedPass");
        assertThat(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))).isTrue();
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(repo.findByUsername("unknown")).thenReturn(null);

        assertThatThrownBy(() -> service.loadUserByUsername("unknown"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found");
    }
}