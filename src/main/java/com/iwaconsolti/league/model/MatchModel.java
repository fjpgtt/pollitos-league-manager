package com.iwaconsolti.league.model;

import lombok.Data;

import java.util.List;

@Data
public class MatchModel {
    private int idmatch;
    private int idleague;
    private TeamModel localteam;
    private TeamModel visitteam;
    private int goallocal;
    private int goalVisit;
}