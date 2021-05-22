package com.example.heroTour;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class Hero  extends PanacheEntity {

    private String name;
    private int votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
