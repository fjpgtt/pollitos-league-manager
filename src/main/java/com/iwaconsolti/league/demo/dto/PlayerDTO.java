package com.iwaconsolti.league.demo.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PlayerDTO {
    private long id;
    private String name;
    private String team;


    public PlayerDTO(long id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public PlayerDTO(long id, String name) {
    }
}