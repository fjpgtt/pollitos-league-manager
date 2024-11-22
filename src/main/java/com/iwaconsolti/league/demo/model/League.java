package com.iwaconsolti.league.demo.model;

import jakarta.persistence.*;
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
@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LeagueType type;
    private int maxTeams;
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<Match> matchList = new ArrayList<>();
}