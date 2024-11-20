package com.iwaconsolti.league.controller;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MatchModel> getMatchById(@PathVariable int id) {
        return service.getMatchById(id);
    }

    @PostMapping("/")
    public MatchModel addMatch (@RequestBody MatchModel matchmodel) {
        return service.insertMatch(matchmodel );
    }

    @DeleteMapping("/matches")
    public boolean DeleteMatch() {
        return service.deleteAllMatches();
    }
}