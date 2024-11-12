package com.iwaconsolti.league.demo.dto.request;

public record MatchCreateRequest(
        long homeTeamId,
        long awayTeamId,
        int homeScore,
        int awayScore
) {}