package com.pulselive.service;

import com.pulselive.model.LeagueTableEntry;
import com.pulselive.model.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeagueTable {
    private static final String LOST = "lost";
    private static final String WON = "won";
    private static final String DRAWN = "drawn";

    private List<LeagueTableEntry> tableEntryList = new ArrayList<>();

    public LeagueTable(final List<Match> matches) {
        createLeagueTable(matches);
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return
     */
    public List<LeagueTableEntry> getTableEntries() {
        Comparator<LeagueTableEntry> entryComparator = Comparator.comparing(LeagueTableEntry::getPoints)
                                                                 .thenComparing(LeagueTableEntry::getGoalDifference)
                                                                 .thenComparing(LeagueTableEntry::getGoalsFor)
                                                                 .thenComparing(LeagueTableEntry::getTeamName);

        List<LeagueTableEntry> tableEntries = new ArrayList<>(tableEntryList);

        tableEntries.sort(entryComparator.reversed());

        return tableEntryList;
    }

    private void createLeagueTable(List<Match> matches) {
        matches.forEach(match -> {
            String gameResult = retrieveGameResult(match);
            if(!isTeamExistsInTable(match.getHomeTeam())){
                LeagueTableEntry tableEntry = createLeagueEntry(match, gameResult);
                tableEntryList.add(tableEntry);
            }else {
                updateLeagueEntry(match, gameResult);
            }
        });
    }

    private LeagueTableEntry createLeagueEntry(Match match, String gameResult) {

        int matchesPlayed = 1;
        int matchesWon = 0;
        int matchesLost = 0;
        int matchesDrawn = 0;
        int matchPoints = 0;
        int goalsFor = match.getHomeScore();
        int goalsAgainst = match.getAwayScore();
        int goalDifference = goalsFor - goalsAgainst;

        if(gameResult.equals(WON)){
            matchesWon = 1;
            matchPoints = 3;
        }else if(gameResult.equals(LOST)){
            matchesLost = 1;
        }else{
            matchesDrawn = 1;
            matchPoints = 1;
        }

        return LeagueTableEntry.builder()
                               .teamName (match.getHomeTeam())
                               .played(matchesPlayed)
                               .won(matchesWon)
                               .drawn(matchesDrawn)
                               .lost(matchesLost)
                               .goalsFor(goalsFor)
                               .goalsAgainst(goalsAgainst)
                               .goalDifference(goalDifference)
                               .points(matchPoints)
                               .build();
    }

    private void updateLeagueEntry(Match match, String gameResult) {
        LeagueTableEntry leagueEntry = tableEntryList.stream()
                                                     .filter(tableEntry -> tableEntry.getTeamName().equalsIgnoreCase(match.getHomeTeam()))
                                                     .findFirst()
                                                     .orElseThrow(() -> new IllegalArgumentException("Entry does not exist!"));

        int goalDifference = assignPoints(match, gameResult, leagueEntry);

        leagueEntry.setPlayed(leagueEntry.getPlayed() + 1);
        leagueEntry.setGoalsFor(leagueEntry.getGoalsFor() + match.getHomeScore());
        leagueEntry.setGoalsAgainst(leagueEntry.getGoalsAgainst() + match.getAwayScore());
        leagueEntry.setGoalDifference(leagueEntry.getGoalDifference() + goalDifference);
    }

    private int assignPoints(Match match, String gameResult, LeagueTableEntry leagueEntry) {
        if(gameResult.equals(WON)){
            leagueEntry.setPoints(leagueEntry.getPoints() + 3);
            leagueEntry.setWon(leagueEntry.getWon() + 1);
        }else if(gameResult.equals(LOST)){
            leagueEntry.setLost(leagueEntry.getLost() + 1);
        }else{
            leagueEntry.setPoints(leagueEntry.getPoints() + 1);
            leagueEntry.setDrawn(leagueEntry.getDrawn() + 1);
        }
        return match.getHomeScore() - match.getAwayScore();
    }

    private boolean isTeamExistsInTable(String homeTeam) {
        return tableEntryList.stream().anyMatch(leagueTableEntry -> leagueTableEntry.getTeamName().equalsIgnoreCase(homeTeam));
    }

    public String retrieveGameResult(Match match) {
        String result;

        if(match.getHomeScore() > match.getAwayScore()){
            result = WON;
        }else if(match.getHomeScore() < match.getAwayScore()){
            result = LOST;
        }else {
            result = DRAWN;
        }

        return result;
    }
}