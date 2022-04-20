package com.monopoly.service;

import com.monopoly.entity.Player;
import com.monopoly.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> optionalPlayer = playerRepository.findByUsername(username);
        if(optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            return new User(player.getUsername(), player.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Incorrect username or password.");
    }

}
