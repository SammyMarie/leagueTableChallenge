package com.pulselive.data;

import com.pulselive.model.Match;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Match> populateMatches(){
        Match game1 = new Match("Liverpool", "Tottenham", 2, 1);
        Match game2 = new Match("Tottenham", "Liverpool", 3, 3);
        Match game3 = new Match("Barcelona", "Atletico Madrid", 1, 3);
        Match game4 = new Match("Atletico Madrid", "Barcelona", 1, 0);
        Match game5 = new Match("Liverpool", "Barcelona", 0, 2);
        Match game6 = new Match("Barcelona", "Liverpool", 1, 0);
        Match game7 = new Match("Tottenham", "Atletico Madrid", 3, 0);
        Match game8 = new Match("Atletico Madrid", "Chelsea", 3, 3);
        Match game9 = new Match("Tottenham", "Barcelona", 1, 1);
        Match game10 = new Match("Barcelona", "Tottenham", 2, 2);
        Match game11 = new Match("Liverpool", "Atletico Madrid", 1, 0);
        Match game12 = new Match("Atletico Madrid", "Liverpool", 2, 4);

        List<Match> matches = new ArrayList<>();
        matches.add(game1);
        matches.add(game2);
        matches.add(game3);
        matches.add(game4);
        matches.add(game5);
        matches.add(game6);
        matches.add(game7);
        matches.add(game8);
        matches.add(game9);
        matches.add(game10);
        matches.add(game11);
        matches.add(game12);

        return matches;
    }
}
