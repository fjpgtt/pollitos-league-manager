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
public class Team {
    private long id;
    private String name;
    private List<Player> playerList = new ArrayList<>();
    private League league;
}