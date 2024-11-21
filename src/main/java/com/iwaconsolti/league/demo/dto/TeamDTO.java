package com.iwaconsolti.league.demo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TeamDTO {
    private int ID;
    private int score;
    private String name;
    private List<PlayerDTO> playerDTOS = new ArrayList<>();

    public TeamDTO(String name, int score, int ID) {
        this.name = name;
        this.ID = ID;
        this.score = score;
    }

    public void addPlayer(PlayerDTO playerDTO){
        playerDTOS.add(playerDTO);
    }

}
