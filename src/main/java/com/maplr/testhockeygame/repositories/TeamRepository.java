package com.maplr.testhockeygame.repositories;

import com.maplr.testhockeygame.beans.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByYear(Integer year);
}
