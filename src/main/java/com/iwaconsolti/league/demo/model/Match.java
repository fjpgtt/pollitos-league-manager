package com.iwaconsolti.league.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    @JsonBackReference
    private League league;
}