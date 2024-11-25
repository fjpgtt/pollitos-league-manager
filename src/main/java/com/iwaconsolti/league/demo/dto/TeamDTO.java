package com.iwaconsolti.league.demo.dto;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private long ID;
    private int score;
    private String name;
    private List<PlayerDTO> playerDTOS = new ArrayList<>();
    private String league;

    public TeamDTO(String name, int score, long ID, String league) {
        this.name = name;
        this.ID = ID;
        this.score = score;
        this.league = league;
    }


    public void addPlayer(PlayerDTO playerDTO){
        playerDTOS.add(playerDTO);
    }

}
