package com.iwaconsolti.league.demo.dto.response;

public record MatchSummaryResponse(
        long id,
        TeamSummaryResponse homeTeam,
        TeamSummaryResponse awayTeam,
        int homeScore,
        int awayScore
) {}