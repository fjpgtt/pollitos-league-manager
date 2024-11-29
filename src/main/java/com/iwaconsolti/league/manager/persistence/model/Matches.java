package com.iwaconsolti.league.manager.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Matches {

    @Id
    @Column(name = "id_match", nullable = false)
    @GeneratedValue
    private int idMatch;
    @Column
    private String leagueType;
    @Column(name = "team_id_a")
    private int teamIdA;
    @Column(name = "team_id_b")
    private int teamIdB;
    @Column(name = "team_score_a")
    private int teamScoreA;
    @Column(name = "team_score_b")
    private int teamScoreB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id_a", insertable = false, updatable = false)
    private Teams teamA;
    @OneToOne
    @JoinColumn(name = "team_id_b", insertable = false, updatable = false)
    private Teams teamB;

    public Matches(String leagueType, int teamIdA, int teamIdB, int teamScoreA, int teamScoreB) {
        this.leagueType = leagueType;
        this.teamIdA = teamIdA;
        this.teamIdB = teamIdB;
        this.teamScoreA = teamScoreA;
        this.teamScoreB = teamScoreB;
    }
}
