package net.fantamiglia.league.core;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeagueService {

    @Autowired
    private LeagueRepository repo;

    public League insertLeague(League league) {
        repo.insert(league);
        return league;
    }

    public League findOneLeague(String id) {
        return repo.findOne(id);
    }

    public League saveLeague(String id, League league) {
        league.setId(id);
        repo.save(league);
        return league;
    }

    public List<League> findAllLeagues() {
        return repo.findAll();
    }

    public boolean deleteLeague(String leagueId) {
        repo.delete(leagueId);
        return true;
    }

}
