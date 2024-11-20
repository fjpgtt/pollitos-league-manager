package com.iwaconsolti.league.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "match")
public class MatchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmatch")
    private int idmatch;

    @Column(name = "idleague")
    private int idleague;

@Column (name = "localteamid")
    private int localteamid;

@Column(name = "visitteamid")
    private int visitteamid;

    @Column(name = "goallocal")
    private int goallocal;

    @Column(name = "goalVisit")
    private int goalVisit;
}
