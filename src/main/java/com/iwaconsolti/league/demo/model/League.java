package com.iwaconsolti.league.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class League {
    private long id;
    private String name;
    private LeagueType type;
    private int maxTeams;
    private List<Team> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
}