package com.monopoly.service;

import com.monopoly.config.EmailConfigurer;
import com.monopoly.entity.PasswordResetToken;
import com.monopoly.entity.Player;
import com.monopoly.repository.PasswordResetTokenRepository;
import com.monopoly.repository.PlayerRepository;
import com.monopoly.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationProvider authenticationProvider;

    private final JwtUtil jwtUtil;

    private final EmailConfigurer emailConfigurer;

    private void CreatePasswordResetToken(Player player, String token){
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setPlayer(player);
        passwordResetTokenRepository.save(myToken);
    }


    public ResponseEntity<List<Player>> getAllPlayers() {
        return  ResponseEntity.ok().body(playerRepository.findAll());
    }

    public ResponseEntity<Player> registerPlayer(Player player) {

        if (playerRepository.existsByUsername(player.getUsername())) {
            return null;
        }
        if (playerRepository.existsByEmail(player.getEmail())) {
            return null;
        }

        player.setPassword(passwordEncoder.encode(player.getPassword()));
        return ResponseEntity.ok().body(playerRepository.save(player));

    }

    public ResponseEntity<String> loginPlayer(Player player) {
        if(player.getUsername().isEmpty() || player.getPassword().isEmpty()){
            return (ResponseEntity.badRequest()).body("username and password cannot be empty");
        }

        else{
            Optional<Player> optionalPlayer = playerRepository.findByUsername(player.getUsername());
            if(optionalPlayer.isPresent()){
                Authentication authentication = new UsernamePasswordAuthenticationToken(player.getUsername(),player.getPassword());
                try{
                    Authentication authenticatePlayer = authenticationProvider.authenticate(authentication);
                    String jwtToken = jwtUtil.generateToken(authenticatePlayer);
                    return ResponseEntity.ok().body(jwtToken);
                }
                catch (AuthenticationException e){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.toString());
                }
                catch (Exception e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("incorrect username or password.");
            }
        }

    }



    public ResponseEntity<String> resetPassword(String email) {
        Optional<Player> optionalPlayer = playerRepository.findByEmail(email);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            String token = UUID.randomUUID().toString();
            CreatePasswordResetToken(player, token);
            sendEmail(token, player);
            return ResponseEntity.ok().body("password reset email sent.");
        } else {
            return ResponseEntity.badRequest().body("user not found.");
        }
    }




    private void sendEmail(String token, Player player){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfigurer.getHost());
        mailSender.setPort(emailConfigurer.getPort());
        mailSender.setUsername(emailConfigurer.getUsername());
        mailSender.setPassword(emailConfigurer.getPassword());
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.starttls.enable","true");
        mailSender.setJavaMailProperties(prop);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("");
        mailMessage.setTo(player.getEmail());
        mailMessage.setSubject("password reset");
        mailMessage.setText(token);
        mailSender.send(mailMessage);
    }


}
