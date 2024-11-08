package com.iwaconsolti.league.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    private Long id;
    private String team1;
    private String team2;
}
