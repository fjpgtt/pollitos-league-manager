package com.iwaconsolti.league.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlayerDTO {
    private int id;
    private String name;
    private String team;


    public PlayerDTO(int id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

}