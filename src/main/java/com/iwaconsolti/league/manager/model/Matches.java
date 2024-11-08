package com.iwaconsolti.league.manager.model;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.Data;

@Data
public class Matches {
    private Teams team;
    private Long scoreTeam;

    public Matches(Teams team, Long scoreTeam) {
        this.team = team;
        this.scoreTeam = scoreTeam;
    }

    public Matches(Matches match) {
        this.team = match.getTeam();
        this.scoreTeam = match.getScoreTeam();
    }

    public Matches() {}

}
