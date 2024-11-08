package com.iwaconsolti.league.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    private Long id;
    private LeagueType name;
    private Matches matches;
    private Player player;
    private Team team;
}
