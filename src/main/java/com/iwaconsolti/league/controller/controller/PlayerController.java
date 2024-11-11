package com.iwaconsolti.league.controller.controller;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Player")
@Slf4j
public class PlayerController {

    private final PlayerService service;
    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }
    @GetMapping("/{idTeam}")
    public PlayerModel getPlayerById(@PathVariable Integer idTeam) {
        if (idTeam == null) {
            System.out.println("El idPlayer proporcionado es null");
            return null;
        }
        return service.getPlayerById(idTeam);
    }
    @PostMapping("/")
    public PlayerModel addPlayer(@RequestBody PlayerModel playermodel) {

        return service.insertPlayer(playermodel );
    }
    @PutMapping("/{id}")
    public PlayerModel updateTeam (@PathVariable int id, @RequestBody PlayerModel player) {

        return service.updatePlayer(id, player);
    }

}