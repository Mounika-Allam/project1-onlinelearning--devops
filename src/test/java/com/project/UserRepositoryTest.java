package com.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.model.User;
import com.project.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
        assertThat(savedUser.getRole()).isEqualTo("USER");
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("findme");
        user.setPassword("pass123");
        user.setRole("USER");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("findme");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("findme");
        assertThat(foundUser.getRole()).isEqualTo("USER");
    }
}