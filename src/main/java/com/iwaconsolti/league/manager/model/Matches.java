package com.iwaconsolti.league.manager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Matches {

    private Teams team;
    private Map <Long, Long> scoreTeam;

    public Matches(Matches match) {
        this.team = match.getTeam();
        this.scoreTeam = match.getScoreTeam();
    }
}
