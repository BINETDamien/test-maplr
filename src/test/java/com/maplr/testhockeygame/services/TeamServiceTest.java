package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.repositories.TeamRepository;
import com.maplr.testhockeygame.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    private TeamServiceImpl teamService;
    @Mock
    private TeamRepository teamRepository;

    @Test
    void testAddOnePlayer_OK() {

        //GIVEN

        Team team = new Team().setCoach("COACH TEST")
                .setId(1l)
                .setYear(2021)
                .setPlayers(new ArrayList<>());
        when(teamRepository.findByYear(any())).thenReturn(team);
        Player playerToAdd = new Player().setCaptain(true).setLastname("LASTNAME").setName("NAME").setNumber(42);

        //WHEN
        Team teamResult = this.teamService.addPlayerForYear(2021, playerToAdd);
        //THEN
        assertThat(teamResult).extracting(Team::getId, Team::getYear, Team::getCoach)
                .containsExactly(team.getId(), team.getYear(), team.getCoach());
        assertThat(teamResult.getPlayers()).isNotNull().hasSize(1);
        assertThat(teamResult.getPlayers().get(0)).isEqualTo(playerToAdd);

    }

    @Test
    void testAddOnePlayer_KO() {
        //GIVEN
        when(teamRepository.findByYear(any())).thenReturn(null);
        Player playerToAdd = new Player().setCaptain(true).setLastname("LASTNAME").setName("NAME").setNumber(42);

        //WHEN AND THEN
        assertThrows(RuntimeException.class, () -> {
            this.teamService.addPlayerForYear(2021, playerToAdd);
        });
    }

}
