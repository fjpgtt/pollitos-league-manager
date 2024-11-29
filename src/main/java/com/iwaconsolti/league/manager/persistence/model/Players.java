package com.iwaconsolti.league.manager.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Players {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String leagueType;
    @Column
    private String name;
    @Column(name = "team_id")
    private int teamId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Teams team;

    public Players(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }
}
