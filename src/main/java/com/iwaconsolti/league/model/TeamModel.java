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
