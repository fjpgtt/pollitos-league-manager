package com.iwaconsolti.league.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idleague", referencedColumnName = "id_league")
    @Column(name = "idplayer")
    private int idPlayer;

    @Column(name = "idleague")
    private int idLeague;

    @Column(name = "idteam")
    private int idTeam;

    @Column(name = "nameplayer")
    private String namePlayer;




}
