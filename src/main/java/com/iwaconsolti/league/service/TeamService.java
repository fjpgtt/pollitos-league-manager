package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamModel addTeam(TeamModel team) {
        return teamRepository.save(team);
    }

    public List<TeamModel> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public TeamModel updateTeam(Long teamId, TeamModel team) {
        Optional<TeamModel> existingTeam = teamRepository.findById(teamId);

        if (existingTeam.isPresent()) {
            TeamModel updatedTeam = existingTeam.get();
            updatedTeam.setTeamname(team.getTeamname());
            updatedTeam.setIdLeague(team.getIdLeague());
            return teamRepository.save(updatedTeam);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteAllPlayersByTeamId(Long teamId) {
        teamRepository.deletePlayersByTeamId(teamId);
    return true;
    }
}
