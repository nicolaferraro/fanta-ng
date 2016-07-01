package net.fantamiglia.league.core;


import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;

public class League {

    @Id
    private String id;

    private String name;

    private LocalDate start;

    private LocalDate end;

    private Integer rounds;

    public League() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("League{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", rounds=").append(rounds);
        sb.append('}');
        return sb.toString();
    }

}
