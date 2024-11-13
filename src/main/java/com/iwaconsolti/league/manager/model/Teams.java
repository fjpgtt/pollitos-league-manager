package com.iwaconsolti.league.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Teams {
    private int id;
    private String name;
    private List<Players> players = new ArrayList<>();

    public Teams(String name) {
        this.name = name;
    }


}
