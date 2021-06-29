package com.maplr.testhockeygame.services.impl;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.repositories.PlayerRepository;
import com.maplr.testhockeygame.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player setCapitaine(Integer idPlayer) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(idPlayer);
        if (optionalPlayer.isEmpty()) {
            throw new RuntimeException("le player avec l'id " + idPlayer + " n'existe pas");
        }
        return this.playerRepository.save(optionalPlayer.get().setCaptain(true));
    }
}
