package com.iwaconsolti.league.demo.dto.response;

import com.iwaconsolti.league.demo.model.LeagueType;

import java.util.List;

public record LeagueResponse(
        long id,
        String name,
        LeagueType type,
        int maxTeams,
        List<TeamSummaryResponse> teamList,
        List<MatchSummaryResponse> matchList
) {}