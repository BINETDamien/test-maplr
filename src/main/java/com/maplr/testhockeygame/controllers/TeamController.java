package com.maplr.testhockeygame.controllers;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{year}")
    public ResponseEntity<Team> getTeamByYear(@PathVariable("year") Integer year) {
        Team team = this.teamService.getTeamByYear(year);
        return new ResponseEntity<>(team, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public void createTeam(@RequestBody Team team) {
        this.teamService.createTeam(team);
    }

    @PostMapping("/{year}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable("year") Integer year, @RequestBody Player player) {
        Team team = this.teamService.addPlayerForYear(year, player);
        return new ResponseEntity<>(team, HttpStatus.ACCEPTED);
    }
}
