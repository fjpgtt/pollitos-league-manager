package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private List<Player> players = new ArrayList<>();
    private Long nextId = 1L;

    public Player addPlayer(Player player) {
        player.setId(nextId++);
        logger.info("Adding new player: {}", player);
        players.add(player);
        return player;
    }
}
