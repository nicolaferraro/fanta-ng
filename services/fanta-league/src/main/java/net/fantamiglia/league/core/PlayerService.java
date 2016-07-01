package net.fantamiglia.league.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepository repo;

    @Autowired
    private LeagueRepository leagueRepo;

    public Player insertPlayer(String leagueId, Player player) {
        if(!leagueRepo.exists(leagueId)) {
            throw new IllegalArgumentException("Non-existent league id: " + leagueId);
        }

        player.getId().setLeagueId(leagueId);
        repo.insert(player);
        return player;
    }

    public Player findOnePlayer(PlayerId id) {
        return repo.findOne(id);
    }

    public Player savePlayer(PlayerId id, Player player) {
        player.setId(id);
        repo.save(player);
        return player;
    }

    public List<Player> findAllPlayers() {
        return repo.findAll();
    }

    public List<Player> findPlayersByLeague(String league) {
        return repo.findByIdLeagueId(league);
    }

    public boolean deletePlayer(PlayerId playerId) {
        repo.delete(playerId);
        return true;
    }

}
