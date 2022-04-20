package com.monopoly.controller;

import com.monopoly.entity.Player;
import com.monopoly.service.PlayerService;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PostMapping("/register")
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player){ return playerService.registerPlayer(player); }

    @PostMapping("/login")
    public ResponseEntity<String> loginPlayer(@RequestBody Player player){ return playerService.loginPlayer(player); }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody String email) {return playerService.resetPassword(email);}

}
