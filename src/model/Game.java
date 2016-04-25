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

        if (semifinals.size() == 0) {
            for (Round round : quarterRounds) {
                if (round.getWinner() == null) {
                    return round;
                }
            }

            generateSemiFinals();
        }

        if (finalRound == null) {
            for (Round round : semifinals) {
                if (round.getWinner() == null) {
                    return round;
                }
            }

            generateFinal();
        }

        if (finalRound.getWinner() == null) {
            return finalRound;
        }

        return null;
    }

    public Cat getWinner() {
        return finalRound.getWinner();
    }

    private void generateQuarterFinals() {
        for(int i = 0; i < eightFinals.size(); i += 2) {
            Cat catA = eightFinals.get(i).getWinner();
            Cat catB = eightFinals.get(i + 1).getWinner();
            quarterRounds.add(new Round(catA, catB));
        }
    }

    private void generateSemiFinals() {
        for(int i = 0; i < quarterRounds.size(); i += 2) {
            Cat catA = quarterRounds.get(i).getWinner();
            Cat catB = quarterRounds.get(i + 1).getWinner();
            semifinals.add(new Round(catA, catB));
        }
    }

    private void generateFinal() {
        Cat catA = semifinals.get(0).getWinner();
        Cat catB = semifinals.get(1).getWinner();

        finalRound = new Round(catA, catB);
    }
}
