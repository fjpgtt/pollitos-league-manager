package com.iwaconsolti.league.demo.dto.request;

import java.util.List;

public record TeamUpdateRequest(
        String name,
        List<PlayerCreateRequest> playerList
) {}