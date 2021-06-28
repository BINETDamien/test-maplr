package com.maplr.testhockeygame.services.impl;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.repositories.PlayerRepository;
import com.maplr.testhockeygame.repositories.TeamRepository;
import com.maplr.testhockeygame.services.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Team getTeamByYear(Integer year) {
        return this.teamRepository.findByYear(year);
    }

    @Override
    public Team addPlayerForYear(Integer year, Player player) {
        Team teamForYear = this.teamRepository.findByYear(year);
        if (teamForYear == null) {
            throw new Error("La team de l'année " + year + " n'existe pas");
        }
        teamForYear.getPlayers().add(player);
        this.teamRepository.save(teamForYear);
        return teamForYear;
    }

    @Override
    public void createTeam(Team team) {
        this.teamRepository.save(team);
    }
}
