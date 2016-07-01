package net.fantamiglia.league.core;


import org.springframework.data.annotation.Id;

import java.time.Instant;

public class Player {

    @Id
    private PlayerId id;

    private String name;

    private String team;

    private Instant dateOfDeparture;

    public Player() {
    }

    public PlayerId getId() {
        return id;
    }

    public void setId(PlayerId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Instant getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Instant dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", team='").append(team).append('\'');
        sb.append(", dateOfDeparture=").append(dateOfDeparture);
        sb.append('}');
        return sb.toString();
    }
}
