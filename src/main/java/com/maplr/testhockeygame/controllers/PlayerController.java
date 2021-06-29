package com.maplr.testhockeygame.controllers;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PutMapping("/captain/{id}")
    public ResponseEntity<Player> setCapitaine(@PathVariable("id") Integer id) {
        Player player = this.playerService.setCapitaine(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
