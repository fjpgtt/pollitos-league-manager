package com.iwaconsolti.league.controller;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Match")
@Slf4j
public class MatchController {

    private final MatchService service;
    @Autowired
    public MatchController(MatchService service) {
        this.service = service;
    }

    @GetMapping("/team/{id}/{idLeague}")
    public List<MatchModel> getMatchById(@PathVariable int id, @PathVariable int idLeague) {
        return service.getMatchById(id, idLeague);
    }

    @PostMapping("/")
    public MatchModel addMatch (@RequestBody MatchModel matchmodel) {
        return service.insertMatch(matchmodel );
    }

    @DeleteMapping("/matches")
    public boolean DeleteMatch() {
        return service.deleteAllMatches();
    }

    @GetMapping("/team/league{idLeague}")
    public List<MatchModel> getMatchByIdLeague(@PathVariable int idLeague) {
        return service.getMatchByIdLeague(idLeague);
    }

}