package com.monopoly.service;

import com.monopoly.entity.Player;
import com.monopoly.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player registerPlayer(Player player) {

        if (playerRepository.existsByUsername(player.getUsername())) {
            return null;
        }
        if (playerRepository.existsByEmail(player.getEmail())) {
            return null;
        }

        return playerRepository.save(player);

    }

    public ResponseEntity<List<Player>> getAllPlayers() {
        return  ResponseEntity.ok().body(playerRepository.findAll());
    }
}
