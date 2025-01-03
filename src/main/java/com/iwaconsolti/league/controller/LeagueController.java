package com.iwaconsolti.league.controller;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping
    public List<LeagueModel> getAllLeagues() {
        return leagueRepository.findAllLeagues();
    }
}
