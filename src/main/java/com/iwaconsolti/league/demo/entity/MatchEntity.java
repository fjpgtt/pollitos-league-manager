package com.iwaconsolti.league.demo.entity;

import com.iwaconsolti.league.demo.dto.TeamDTO;
import jakarta.persistence.*;

@Entity
@Table(name="matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @ManyToOne
    @JoinColumn(name="team1", nullable = false)
    private TeamEntity team1;

    @ManyToOne
    @JoinColumn(name="team2", nullable = false)
    private TeamEntity team2;
}
