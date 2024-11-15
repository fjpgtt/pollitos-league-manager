package com.iwaconsolti.league.demo.dto.request;

import com.iwaconsolti.league.demo.model.LeagueType;

public record LeagueCreateRequest(
        String name,
        LeagueType type,
        int maxTeams
) {}