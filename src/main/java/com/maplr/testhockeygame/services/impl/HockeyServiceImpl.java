package com.maplr.testhockeygame.services.impl;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.repositories.PlayerRepository;
import com.maplr.testhockeygame.repositories.TeamRepository;
import com.maplr.testhockeygame.services.HockeyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HockeyServiceImpl implements HockeyService {
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public HockeyServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
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
            throw new RuntimeException("La team de l'année " + year + " n'existe pas");
        }
        teamForYear.getPlayers().add(player);
        this.teamRepository.save(teamForYear);
        return teamForYear;
    }

    @Override
    public Player setCapitaine(Integer idPlayer) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(idPlayer);
        if (optionalPlayer.isEmpty()) {
            throw new RuntimeException("le player avec l'id " + idPlayer + " n'existe pas");
        }

        Player player = optionalPlayer.get();
        // le plus optimal ici serai de filtrer directement dans la requete pour ne pas ramener d'éléments inutiles
        // mais pour faire une lambda expression j'ai effectuer le filtre coté java
        List<Team> teams = this.teamRepository.findAll().stream().filter(team -> team.getPlayers().contains(player)).collect(Collectors.toList());
        teams.forEach(team -> team.getPlayers().forEach(player1 -> player1.setIsCaptain(false)));
        player.setIsCaptain(true);
        this.teamRepository.saveAll(teams);

        return player;
    }

    @Override
    public Team createTeam(Team team) {
        return this.teamRepository.save(team);
    }
}
