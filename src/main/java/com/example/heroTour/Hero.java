package com.example.heroTour;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Hero extends PanacheEntity {

    private String name;
    private int votes;

    public static List<Hero> findNameLike(String term) {
        return find("name LIKE ?1", term).list();
    }

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
