package com.iwaconsolti.league.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Setter
@Getter
@ToString
public class MatchDTO {
    private TeamDTO teamDTO1;
    private TeamDTO teamDTO2;

    public MatchDTO(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        //Initialized the match with two teams.
        this.teamDTO1 = teamDTO1;
        this.teamDTO2 = teamDTO2;
        //Creating a random score for teams.
        Random random = new Random();
        teamDTO1.setScore(random.nextInt(3) +1);
        teamDTO2.setScore(random.nextInt(3) +1);
    }




}
