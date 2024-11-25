package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.MatchEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.MatchRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public void createMatch(TeamDTO team1, TeamDTO team2, String league) {
        TeamEntity teamONE = teamRepository.findByName(team1.getName());
        TeamEntity teamTWO = teamRepository.findByName(team2.getName());


        if (teamONE == null && teamTWO == null) {
            teamONE = new TeamEntity();
            teamONE.setName(team1.getName());
            teamONE.setScore(team1.getScore());
            teamONE.setLeague(league);

            teamRepository.save(teamONE);

            teamTWO = new TeamEntity();
            teamTWO.setName(team2.getName());
            teamTWO.setScore(team2.getScore());
            teamTWO.setLeague(league);

            teamRepository.save(teamTWO);
        }
        MatchEntity matchEntity = new MatchEntity(teamONE, teamTWO);
        matchRepository.save(matchEntity);
        log.info("New Match has been created: {} vs {}", teamONE.getName(), teamTWO.getName());
    }

    public void deleteAllMatches() {
        matchRepository.deleteAll();
        log.info("All matches has been deleted");
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(match -> new MatchDTO(
                        new TeamDTO(match.getTeam1().getName(), match.getTeam1().getScore(), match.getTeam1().getID(), match.getTeam1().getLeague()),
                        new TeamDTO(match.getTeam2().getName(), match.getTeam2().getScore(), match.getTeam2().getID(), match.getTeam2().getLeague())))
                .toList();
    }
}
