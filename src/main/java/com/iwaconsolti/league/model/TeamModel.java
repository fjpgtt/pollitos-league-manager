    package com.iwaconsolti.league.model;
    import lombok.Data;
    import java.util.List;

    @Data
    public class    TeamModel {
        private int idteam;
        private int idLeague;
        private String teamname;
        private List<PlayerModel> players;
    }
