package com.iwaconsolti.league.controller.controller;
import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping("/")
    public LeagueModel createLeague(@RequestBody LeagueModel league) {
        LeagueModel createdLeague = leagueService.insertLeague(league);
        return createdLeague;
    }

    @GetMapping("/")
    public List<LeagueModel> getAllLeagues() {
        return leagueService.getAllLeagues();
    }
}
