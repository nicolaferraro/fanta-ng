package net.fantamiglia.league.core;

import java.io.Serializable;

/**
 *
 */
public class PlayerId implements Serializable {

    private static final long serialVersionUID = 1778434183042952147L;

    String leagueId;

    String playerCode;

    public PlayerId() {
    }

    public PlayerId(String leagueId, String playerCode) {
        this.leagueId = leagueId;
        this.playerCode = playerCode;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerId{");
        sb.append("leagueId='").append(leagueId).append('\'');
        sb.append(", playerCode='").append(playerCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
