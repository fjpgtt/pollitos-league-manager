package com.iwaconsolti.league.model;

import lombok.Data;
import java.util.List;

@Data
public class LeagueModel {
    private int idLeague;
    private String nombre;
    private List<TeamModel> equipos;
    private List<PlayerModel> jugadores;
    private List<MatchModel> partidos;
}
