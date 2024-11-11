package com.iwaconsolti.league.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"players", "league"})
public class Team {
    private Long id;
    private String name;

    @JsonManagedReference
    private List<Player> players = new ArrayList<>();

    @JsonBackReference
    private League league;
}