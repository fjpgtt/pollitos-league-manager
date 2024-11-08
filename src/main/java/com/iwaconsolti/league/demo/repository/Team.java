package com.iwaconsolti.league.demo.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Team {
    String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
