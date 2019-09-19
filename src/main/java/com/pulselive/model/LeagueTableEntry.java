package com.pulselive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LeagueTableEntry {
    private String teamName;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int points;

    @Override
    public String toString(){
        return String.format("LeagueTableEntry [TeamName: %s, GoalsFor: %d, GoalDifference: %d, Points: %d]",
                             teamName, goalsFor, goalDifference, points);
    }
}