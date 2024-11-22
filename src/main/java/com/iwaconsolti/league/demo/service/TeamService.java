package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.request.TeamUpdateRequest;
import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.repository.LeagueRepository;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.playerRepository = playerRepository;
    }

    public Team createTeam(long leagueId, Team team) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        if (league.getTeamList().size() >= league.getMaxTeams()) {
            throw new IllegalStateException("League has reached maximum team capacity");
        }

        team.setLeague(league);
        return teamRepository.save(team);
    }

    public Optional<Team> findTeamById(long leagueId, long teamId) {
        return teamRepository.findById(teamId)
                .filter(team -> team.getLeague().getId() == leagueId);
    }

    public List<Team> findTeamsByLeague(long leagueId) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getLeague().getId() == leagueId)
                .collect(Collectors.toList());
    }

    public Team updateTeam(long leagueId, long teamId, TeamUpdateRequest request) {
        Team team = teamRepository.findById(teamId)
                .filter(t -> t.getLeague().getId() == leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        if (request.name() != null) {
            team.setName(request.name());
        }

        if (request.playerList() != null) {
            team.getPlayerList().clear();
            List<Player> newPlayerList = request.playerList().stream()
                    .map(playerRequest -> {
                        Player player = new Player();
                        player.setName(playerRequest.name());
                        player.setTeam(team);
                        return playerRepository.save(player);
                    })
                    .collect(Collectors.toList());
            team.setPlayerList(newPlayerList);
        }

        return teamRepository.save(team);
    }
}
