package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;

public interface HockeyService {
    Team getTeamByYear(Integer year);

    Team addPlayerForYear(Integer year, Player player);

    Team createTeam(Team team);

    Player setCapitaine(Integer idPlayer);
}
