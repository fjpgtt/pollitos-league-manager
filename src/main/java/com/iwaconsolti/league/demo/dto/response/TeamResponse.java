package com.iwaconsolti.league.demo.dto.response;

import java.util.List;

public record TeamResponse(
        long id,
        String name,
        List<PlayerResponse> playerList
) {}