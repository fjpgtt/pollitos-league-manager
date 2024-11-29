package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team1", nullable = false)
    private TeamEntity team1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team2", nullable = false)
    private TeamEntity team2;

    @Column(name = "score_team1", columnDefinition = "INTEGER DEFAULT 0")
    private int scoreTeam1;

    @Column(name = "score_team2", columnDefinition = "INTEGER DEFAULT 0")
    private int scoreTeam2;

    public MatchEntity(TeamEntity teamONE, TeamEntity teamTWO) {
    }
}
