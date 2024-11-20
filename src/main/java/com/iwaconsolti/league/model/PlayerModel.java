package com.iwaconsolti.league.model;

import lombok.Data;


import jakarta.persistence.*;

@Entity
@Data
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idleague", referencedColumnName = "id_league")
    @Column(name = "idplayer")
    private Long idPlayer;

    @Column(name = "idleague")
    private int idLeague;

    @Column(name = "idteam")
    private int idTeam;

    @Column(name = "nameplayer")
    private String namePlayer;




}
