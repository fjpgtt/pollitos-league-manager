package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(name= "name", nullable = false)
    private String name;
    @Column(name = "score", nullable = false)
    private int score;
    @OneToMany(mappedBy = "team")
    private List<PlayerEntity> players = new ArrayList<>();
    
    @Column(name = "league", nullable = false)
    private String league;


    public TeamEntity(long id, String name, int score) {
    }

    public TeamEntity(long id, String teamName) {
    }

    public TeamEntity(String team, int score){

    }

    public TeamEntity(String team) {
    }
}
