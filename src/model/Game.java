package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vspreys on 25/04/16.
 */
public class Game {
    private List<Cat> allCats;
    private List<Round> eightFinals;
    private List<Round> quarterRounds;
    private List<Round> semifinals;
    private Round finalRound;

    public Game(List<Cat> cats) {
        this.allCats = cats;
        eightFinals = new ArrayList<>();
        quarterRounds = new ArrayList<>();
        semifinals = new ArrayList<>();

        for(int i = 0; i < 16; i += 2) {
            Cat catA = allCats.get(i);
            Cat catB = allCats.get(i + 1);
            eightFinals.add(new Round(catA, catB));
        }
    }

    public Round getNextRound() {
        if(quarterRounds.size() == 0) {
            for (Round round : eightFinals) {
                if (round.getWinner() == null) {
                    return round;
                }
            }

            generateQuarterFinals();
        }

        return finalRound;
    }

    private void generateQuarterFinals() {

    }
}
