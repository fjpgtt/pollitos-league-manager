package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@Slf4j
@RequiredArgsConstructor
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;

    @PostMapping("/")
    public ResponseEntity<?> addPlayer(@RequestBody final Player player){
        String team = player.getTeam();
        System.out.println("aquiii" + team);
        logger.info("Team", team);

        logger.info("Create player {}", player);
        Player created = playerService.addPlayer(player);
        return ResponseEntity.ok(created);
    }

        /*@GetMapping("/")
    public ResponseEntity<List<String>> gePlayers(){
        logger.info("GET request to fetch players");
        return ResponseEntity.ok(service.());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        logger.info("GET request to fetch product with id: {}", id);
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    /*@PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        logger.info("POST request to create new product: {}", product);
        Product created = productService.addProduct(product);
        return ResponseEntity.ok(created);
    }*/
}
