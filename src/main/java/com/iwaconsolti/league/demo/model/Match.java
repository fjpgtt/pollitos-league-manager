package com.iwaconsolti.league.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Setter
@Getter
@ToString
public class Match {
    private Team team1;
    private Team team2;

    public Match(Team team1, Team team2) {
        //Initialized the match with two teams.
        this.team1 = team1;
        this.team2 = team2;
        //Creating a random score for teams.
        Random random = new Random();
        team1.setScore(random.nextInt(3) +1);
        team2.setScore(random.nextInt(3) +1);
    }




}
