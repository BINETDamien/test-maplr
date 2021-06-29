package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.beans.Player;
import com.maplr.testhockeygame.repositories.PlayerRepository;
import com.maplr.testhockeygame.services.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    private PlayerRepository playerRepository;
    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @Test
    void setCapitaine_OK() {
        //GIVEN
        Player player = new Player().setCaptain(false).setLastname("LASTNAME").setName("NAME").setNumber(42);
        when(this.playerRepository.findById(any())).thenReturn(Optional.of(player));
        //WHEN
        this.playerService.setCapitaine(42);
        verify(this.playerRepository).save(this.playerArgumentCaptor.capture());
        Player playerToUpdate = this.playerArgumentCaptor.getValue();
        assertThat(playerToUpdate).extracting(Player::getName, Player::getLastname, Player::getNumber, Player::isCaptain)
                .containsExactly("NAME", "LASTNAME", 42, true);
    }

    @Test
    void setCapitaine_KO() {
        //GIVEN
        when(this.playerRepository.findById(any())).thenReturn(Optional.empty());
        //WHEN
        //WHEN AND THEN
        assertThrows(RuntimeException.class, () -> {
            this.playerService.setCapitaine(42);
        });
    }
}
