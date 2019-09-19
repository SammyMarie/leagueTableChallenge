package com.pulselive.service;

import com.pulselive.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pulselive.data.TestData.populateMatches;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LeagueTableTest {

    private LeagueTable leagueTable;

    @BeforeEach
    void setup() {
        List<Match> matches = populateMatches();
        leagueTable = new LeagueTable(matches);
    }

    @Test
    void canCreateObject() {
        assertThat(leagueTable).isNotNull();
    }

    @Test
    void canAddLeagueTableEntriesShouldSucceed() {
        assertThat(leagueTable.getTableEntries()).hasSize(4);
    }

    @Test
    void canRetrieveAGameWonShouldSucceed() {
        Match liverpoolHome = new Match("Liverpool", "Tottenham Hotspur", 2, 1);
        assertThat(leagueTable.retrieveGameResult(liverpoolHome)).isEqualTo("won");
    }

    @Test
    void canRetrieveAGameLostShouldSucceed() {
        Match liverpoolHome = new Match("Barcelona", "Manchester City", 2, 4);
        assertThat(leagueTable.retrieveGameResult(liverpoolHome)).isEqualTo("lost");
    }

    @Test
    void canRetrieveAGameDrawnShouldSucceed() {
        Match liverpoolHome = new Match("Real Madrid", "Atletico Madrid", 2, 2);
        assertThat(leagueTable.retrieveGameResult(liverpoolHome)).isEqualTo("drawn");
    }

    @Test
    void canDisplayMatchesShouldSucceed() {
        String expected = "[LeagueTableEntry [TeamName: Liverpool, GoalsFor: 3, GoalDifference: 0, Points: 6]," +
                          " LeagueTableEntry [TeamName: Tottenham, GoalsFor: 7, GoalDifference: 3, Points: 5]," +
                          " LeagueTableEntry [TeamName: Barcelona, GoalsFor: 4, GoalDifference: -1, Points: 4]," +
                          " LeagueTableEntry [TeamName: Atletico Madrid, GoalsFor: 6, GoalDifference: -1, Points: 4]]";

        assertThat(leagueTable.getTableEntries().toString()).contains(expected);
    }
}