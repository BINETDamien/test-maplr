package com.maplr.testhockeygame.controllers;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.services.HockeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HockeyController {
    private HockeyService hockeyService;

    public HockeyController(HockeyService hockeyService) {
        this.hockeyService = hockeyService;
    }

    @GetMapping("/team/{year}")
    public ResponseEntity<Team> getTeamByYear(@PathVariable("year") Integer year) {
        Team team = this.hockeyService.getTeamByYear(year);
        return new ResponseEntity<>(team, HttpStatus.ACCEPTED);
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = this.hockeyService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PostMapping("/team/{year}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable("year") Integer year, @RequestBody Player player) {
        Team team = this.hockeyService.addPlayerForYear(year, player);
        return new ResponseEntity<>(team, HttpStatus.ACCEPTED);
    }

    @PutMapping("/player/captain/{id}")
    public ResponseEntity<Player> setCapitaine(@PathVariable("id") Integer id) {
        Player playerDTO = this.hockeyService.setCapitaine(id);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }
}
