package com.iwaconsolti.league.demo.dto.response;

public record PlayerResponse(
        long id,
        String name,
        TeamSummaryResponse team
) {}