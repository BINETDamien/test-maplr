package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;

public interface TeamService {
    Team getTeamByYear(Integer year);

    Team addPlayerForYear(Integer year, Player player);

    void createTeam(Team team);
}
