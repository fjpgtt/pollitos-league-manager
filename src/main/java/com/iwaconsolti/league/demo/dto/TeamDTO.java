package com.iwaconsolti.league.demo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private long id;
    private String name;
    private List<PlayerDTO> playerDTOS = new ArrayList<>();
    private String league;

}

