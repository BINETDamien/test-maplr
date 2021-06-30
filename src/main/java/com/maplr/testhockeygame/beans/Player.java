package com.maplr.testhockeygame.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    private Integer number;
    private String name;
    private String lastname;
    private String position;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
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

    public boolean isIsCaptain() {
        return isCaptain;
    }

    public Player setIsCaptain(boolean captain) {
        this.isCaptain = captain;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Player setPosition(String position) {
        this.position = position;
        return this;
    }
}
