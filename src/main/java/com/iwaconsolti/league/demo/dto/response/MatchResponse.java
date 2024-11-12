package com.iwaconsolti.league.demo.dto.response;

public record MatchResponse(
        long id,
        TeamSummaryResponse homeTeam,
        TeamSummaryResponse awayTeam,
        int homeScore,
        int awayScore
) {}