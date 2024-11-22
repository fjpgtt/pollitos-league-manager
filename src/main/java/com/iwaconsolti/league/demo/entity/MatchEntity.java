package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="matches")
@Getter
@Setter
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
