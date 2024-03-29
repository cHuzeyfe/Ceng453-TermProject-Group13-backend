package com.monopoly.repository;

import com.monopoly.entity.Player;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testRegisterPlayer() {

        playerRepository.save(new Player(null, "usernameRepoTest", "passwordRepoTest", "emailRepoTest"));
        assertTrue(playerRepository.existsByUsername("usernameRepoTest"));
        assertTrue(playerRepository.existsByEmail("emailRepoTest"));
    }
}
