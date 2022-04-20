package com.monopoly.repository;

import com.monopoly.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
