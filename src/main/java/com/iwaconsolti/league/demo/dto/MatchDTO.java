package com.iwaconsolti.league.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Random;

@Setter
@Getter
@ToString
public class MatchDTO {
    private TeamDTO teamDTO1;
    private TeamDTO teamDTO2;

    private int scoreTeam1;
    private int scoreTeam2;

    public MatchDTO(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        //Initialized the match with two teams.
        this.teamDTO1 = teamDTO1;
        this.teamDTO2 = teamDTO2;
        //Creating a random score for teams.
        Random random = new Random();
        this.scoreTeam1 = random.nextInt(3) +1;
        this.scoreTeam2 = random.nextInt(3) +1;
    }




}
