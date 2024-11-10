package com.iwaconsolti.league.controller.controller;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Match")
@Slf4j
public class MatchController {

    private final MatchService service;
    @Autowired
    public MatchController(MatchService service) {

        this.service = service;
    }

    @GetMapping("/team/{id}")
    public MatchModel getMatchById(@PathVariable int id) {
        return service.getMatchById(id); // Llamada correcta al método no estático
    }



    @PostMapping("/")
    public MatchModel addMatch (@RequestBody MatchModel matchmodel) {

        return service.insertMatch(matchmodel );
    }
    @PutMapping("/{id}")
    public MatchModel updateMatch (@PathVariable int id, @RequestBody MatchModel match) {

        return service.updateMatch(id, match);
    }
    @DeleteMapping("/matches")
    public boolean DeleteMatch() {

        return service.deleteMatch();
    }

}