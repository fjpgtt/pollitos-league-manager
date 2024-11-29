package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.MatchEntity;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.MatchRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    public void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        TeamEntity teamONE = teamRepository.findByName(teamDTO1.getName());
        TeamEntity teamTWO = teamRepository.findByName(teamDTO2.getName());

        if (teamONE == null) {
            log.info("Team one doesn't exist.");
            teamONE = teamService.createTeam(teamDTO1);
        }
        if(teamTWO == null){
            log.info("Team one doesn't exist.");
            teamTWO = teamService.createTeam(teamDTO2);
        }

        if (!teamDTO1.getLeague().equalsIgnoreCase(teamDTO2.getLeague())) {
            log.error("Teams have different leagues {} and {}", teamDTO1.getLeague(), teamDTO2.getLeague());
        }

        MatchEntity matchEntity = new MatchEntity(teamONE, teamTWO);
        matchRepository.save(matchEntity);
        log.info("New Match has been created: {} vs {}", teamONE.getName(), teamTWO.getName());
    }

    public void deleteAllMatches() {
        if(matchRepository.count() == 0){
            log.warn("No matches found to delete");
            return;
        }
        matchRepository.deleteAll();
        log.info("All matches have been deleted");
    }

    public List<MatchDTO> getAllMatches() {
        List<MatchDTO> matches = matchRepository.findAll().stream()
                .map(match -> new MatchDTO(
                        new TeamDTO(match.getTeam1().getId(), match.getTeam1().getName(), convertPlayerToDTOList(match.getTeam1().getPlayers()), match.getTeam1().getLeague()),
                        new TeamDTO(match.getTeam2().getId(), match.getTeam2().getName(), convertPlayerToDTOList(match.getTeam2().getPlayers()), match.getTeam2().getLeague())
                ))
                .toList();
        if (matches.isEmpty()) {
            log.info("No matches found in this repo");
        }
        return matches;
    }


    public List<PlayerDTO> convertPlayerToDTOList(List<PlayerEntity> playerEntities) {

        return playerEntities.stream()
                .map(playerEntity -> new PlayerDTO(
                        playerEntity.getId(),
                        playerEntity.getName(),
                        playerEntity.getTeam().getName())
                )
                .collect(Collectors.toList());
    }

}
