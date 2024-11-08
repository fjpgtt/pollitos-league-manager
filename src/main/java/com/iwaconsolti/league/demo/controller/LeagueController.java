package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.repository.TeamRepository;
import com.iwaconsolti.league.demo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class LeagueController {

    private final DemoService demoService;
    private final TeamRepository teamRepository;
    public LeagueController(DemoService demoService, TeamRepository teamRepository) {
        this.demoService = demoService;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/team")
    public String getTeam(@RequestParam String a){
        return demoService.getAllTeams();
    }


}
