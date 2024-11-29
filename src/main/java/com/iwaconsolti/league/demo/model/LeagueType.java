package com.iwaconsolti.league.demo.model;

import lombok.Getter;

import java.util.Arrays;


@Getter
public enum LeagueType {
    SOCCER_LEAGUE("soccerleague"),
    BASKET_LEAGUE("basketleague");

    private final String value;

    LeagueType(String value) {
        this.value = value;
    }

    public static boolean isValidLeague(String leagueName) {
        return Arrays.stream(LeagueType.values())
                .anyMatch(league -> league.getValue().equalsIgnoreCase(leagueName));
    }


}
