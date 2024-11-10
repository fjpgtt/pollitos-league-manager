package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final List<MatchModel> matchList = new ArrayList<>();
    private final TeamService teamService; // Inyección de dependencias para TeamService
    TeamModel equipoVisitante;
    TeamModel equipoLocal;
    @Autowired
    public MatchService(TeamService teamService) {
        this.teamService = teamService;

    }

    // Método para insertar un nuevo partido
    public MatchModel insertMatch(MatchModel match) {
      this.equipoLocal = teamService.getTeamById(match.getEquipoLocal().getId());
        // Obtener el equipo visitante por su ID
        this.equipoVisitante = teamService.getTeamById(match.getEquipoVisitante().getId());

        // Validar si ambos equipos existen antes de añadir el partido
        if (equipoLocal != null && equipoVisitante != null) {
            match.setEquipoLocal(equipoLocal);
            match.setEquipoVisitante(equipoVisitante);
            matchList.add(match); // Añadir el partido a la lista de partidos
            return match;
        }

        // Retornar null si alguno de los equipos no fue encontrado
        return null;
    }


    // Obtener todos los partidos
    public List<MatchModel> getMatch() {
        return matchList;
    }

    // Actualizar un partido por ID
    public MatchModel updateMatch(int id, MatchModel match) {
        for (MatchModel aux : matchList) {
            if (aux.getIdPartido() == id) {
                aux.setEquipoLocal(match.getEquipoLocal());
                aux.setEquipoVisitante(match.getEquipoVisitante());
                aux.setGolesLocal(match.getGolesLocal());
                aux.setGolesVisitante(match.getGolesVisitante());
                return aux;
            }
        }
        return null;
    }

    // Eliminar un partido por ID
    public boolean deleteMatch() {
        matchList.clear();
        return true;
    }

    // Obtener un partido por ID
    public MatchModel getMatchById(int matchId) {
        for (MatchModel match : matchList) {
            if (match.getIdPartido() == matchId) {
                // Asignamos los equipos locales y visitantes
                this.equipoLocal = match.getEquipoLocal();
                this.equipoVisitante = match.getEquipoVisitante();

                // Retornamos el partido encontrado
                return match;
            }
        }
        // Si no se encuentra el partido, retornamos null
        return null;
    }
}
