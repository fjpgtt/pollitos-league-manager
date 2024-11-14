package com.iwaconsolti.league.manager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Matches {

    private int idMatch;
    private String nameTeam1;
    private String nameTeam2;
    private Map<String, Integer> scoreTeam;
}
