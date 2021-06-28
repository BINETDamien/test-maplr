package com.maplr.testhockeygame.beans;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Team {
    @Id
    private Long id;
    private String coach;
    private Integer year;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCoach() {
        return coach;
    }

    public Team setCoach(String coach) {
        this.coach = coach;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public Team setYear(Integer year) {
        this.year = year;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Team setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }
}
