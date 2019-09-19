package com.pulselive.model;

import lombok.Getter;

@Getter
public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}