package com.iwaconsolti.league.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "team")
public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idteam")
    private int idteam;

    private int idLeague;

    @Column(name = "teamname")
    private String teamname;


}
