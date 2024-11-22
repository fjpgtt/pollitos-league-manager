package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{teamName}")
    public List<PlayerEntity> getPlayersByTeam(@PathVariable String teamName) {
        return playerService.getPlayersByTeam(teamName);
    }

    @DeleteMapping("/delete/{teamName}")
    public void deletePlayersByTeam(@PathVariable String teamName) {
        playerService.deletePlayersByTeam(teamName);
    }

    @PostMapping
    public ResponseEntity<PlayerEntity> createPlayer(@RequestBody PlayerEntity player) {
        PlayerEntity savedPlayer = playerService.createPlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

}
