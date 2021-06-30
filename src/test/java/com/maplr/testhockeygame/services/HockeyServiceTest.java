package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.beans.Team;
import com.maplr.testhockeygame.repositories.PlayerRepository;
import com.maplr.testhockeygame.repositories.TeamRepository;
import com.maplr.testhockeygame.services.impl.HockeyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HockeyServiceTest {

    @InjectMocks
    private HockeyServiceImpl hockeyService;
    @Mock
    private TeamRepository teamRepository;


    @Mock
    private PlayerRepository playerRepository;
    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;


    @Test
    void testAddOnePlayer_OK() {

        //GIVEN

        Team team = new Team().setCoach("COACH TEST")
                .setId(1l)
                .setYear(2021)
                .setPlayers(new ArrayList<>());
        when(teamRepository.findByYear(any())).thenReturn(team);
        Player playerToAdd = new Player().setIsCaptain(true).setLastname("LASTNAME").setName("NAME").setNumber(42);

        //WHEN
        Team teamResult = this.hockeyService.addPlayerForYear(2021, playerToAdd);
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
        Player playerToAdd = new Player().setIsCaptain(true).setLastname("LASTNAME").setName("NAME").setNumber(42);

        //WHEN AND THEN
        assertThrows(RuntimeException.class, () -> {
            this.hockeyService.addPlayerForYear(2021, playerToAdd);
        });
    }

    @Test
    void setCapitaine_OK() {
        //GIVEN
        Player player = new Player().setIsCaptain(false).setLastname("LASTNAME").setName("NAME").setNumber(42);
        when(this.playerRepository.findById(any())).thenReturn(Optional.of(player));
        //WHEN
        this.hockeyService.setCapitaine(42);
        verify(this.playerRepository).save(this.playerArgumentCaptor.capture());
        Player playerToUpdate = this.playerArgumentCaptor.getValue();
        assertThat(playerToUpdate).extracting(Player::getName, Player::getLastname, Player::getNumber, Player::isIsCaptain)
                .containsExactly("NAME", "LASTNAME", 42, true);
    }

    @Test
    void setCapitaine_KO() {
        //GIVEN
        when(this.playerRepository.findById(any())).thenReturn(Optional.empty());
        //WHEN
        //WHEN AND THEN
        assertThrows(RuntimeException.class, () -> {
            this.hockeyService.setCapitaine(42);
        });
    }

}
