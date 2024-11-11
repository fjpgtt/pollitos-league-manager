package com.iwaconsolti.league.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"teams", "matches"})
public class League {
    private Long id;
    private String name;
    private LeagueType type;
    private int maxTeams;

    @JsonManagedReference
    private List<Team> teams = new ArrayList<>();

    @JsonManagedReference
    private List<Matches> matches = new ArrayList<>();
}