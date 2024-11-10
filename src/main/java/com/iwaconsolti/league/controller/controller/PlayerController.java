package com.iwaconsolti.league.controller.controller;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Player")
@Slf4j
public class PlayerController {

    private final PlayerService service;
    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<PlayerModel>> getPlayer(){
        log.info("enter");
        return ResponseEntity.ok(service.getPlayer());
    }
    @PostMapping("/")
    public PlayerModel addPlayer(@RequestBody PlayerModel playermodel) {

        return service.insertPlayer(playermodel );
    }
    @PutMapping("/{id}")
    public PlayerModel updateTeam (@PathVariable int id, @RequestBody PlayerModel player) {

        return service.updatePlayer(id, player);
    }
    @DeleteMapping("/{id}")
    public boolean DeletePlayer(@PathVariable int id) {

        return service.deletePlayer(id);
    }

}