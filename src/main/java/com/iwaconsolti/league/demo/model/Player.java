package com.iwaconsolti.league.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team")
public class Player {
    private Long id;
    private String name;

    @JsonBackReference
    private Team team;
}