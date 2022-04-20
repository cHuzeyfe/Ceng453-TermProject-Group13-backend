package com.monopoly.controller;


import com.monopoly.entity.Player;
import com.monopoly.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerControllerTest {

    @Autowired
    private PlayerService playerController;

    @Test
    public void registerPlayerTest() {
        Player player = new Player();
        player.setUsername("player1");
        player.setPassword("password1");
        player.setEmail("email1");
        Player newPlayer = playerController.registerPlayer(player);
        Assert.assertEquals(player, newPlayer);
    }

}
