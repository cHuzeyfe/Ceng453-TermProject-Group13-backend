package com.monopoly.service;

import com.monopoly.entity.Player;
import com.monopoly.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerService playerService;




    @Test
    public void testRegisterPlayer(){
        Player player;
        player = new Player();
        player.setUsername("usernameServiceTest");
        player.setPassword( "passwordServiceTest");
        player.setEmail("emailServiceTest");

        when(playerRepository.existsByUsername(anyString())).thenReturn(false);
        when(playerRepository.save(any())).thenReturn(player);

        Player newPlayer = playerService.registerPlayer(player);
        assertNotNull(newPlayer);
        verify(playerRepository).save(any());
    }

    @Test
    public void testRegisterPlayerExistingUsername(){
        Player player;
        player = new Player();
        player.setUsername("usernameServiceTest");
        player.setPassword( "passwordServiceTest");
        player.setEmail("emailServiceTest");

        when(playerRepository.existsByUsername(anyString())).thenReturn(true);

        Player newPlayer = playerService.registerPlayer(player);

        assertNull(newPlayer);
    }

    @Test
    public void testRegisterPlayerExistingEmail(){
        Player player;
        player = new Player();
        player.setUsername("usernameServiceTest");
        player.setPassword( "passwordServiceTest");
        player.setEmail("emailServiceTest");

        when(playerRepository.existsByEmail(anyString())).thenReturn(true);

        Player newPlayer = playerService.registerPlayer(player);

        assertNull(newPlayer);
    }

}
