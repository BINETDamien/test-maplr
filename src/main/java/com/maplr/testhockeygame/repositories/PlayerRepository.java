package com.maplr.testhockeygame.repositories;

import com.maplr.testhockeygame.beans.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
