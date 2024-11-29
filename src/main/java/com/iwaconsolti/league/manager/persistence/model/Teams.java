package com.iwaconsolti.league.manager.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String leagueType;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Players> players = new ArrayList<>();

    public Teams(String name) {
        this.name = name;
    }


}
