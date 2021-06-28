package com.maplr.testhockeygame.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    private Integer number;
    private String name;
    private String lastname;
    private boolean isCaptain;

    public Integer getNumber() {
        return number;
    }

    public Player setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Player setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public Player setCaptain(boolean captain) {
        isCaptain = captain;
        return this;
    }
}
