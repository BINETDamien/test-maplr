package com.maplr.testhockeygame.repositories;

import com.maplr.testhockeygame.beans.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByYear(Integer year);
}
