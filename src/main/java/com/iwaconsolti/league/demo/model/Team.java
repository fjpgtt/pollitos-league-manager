package com.iwaconsolti.league.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Long id;
    private String name;
    private List<Player> players;
}
