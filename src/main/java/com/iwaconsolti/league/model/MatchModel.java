package com.iwaconsolti.league.model;

import lombok.Data;

import java.util.List;

@Data
public class MatchModel {
    private int idPartido;
    private int idLiga;// Identificador único del partido
    private TeamModel equipoLocal; // Equipo local
    private TeamModel equipoVisitante; // Equipo visitante
    private int golesLocal;        // Goles anotados por el equipo local
    private int golesVisitante;    // Goles anotados por el equipo visitante
}
