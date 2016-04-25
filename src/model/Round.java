package model;

/**
 * Created by vspreys on 25/04/16.
 */
public class Round {
    private Cat participantA;
    private Cat participantB;
    private Cat winner;

    public Round (Cat participantA, Cat participantB) {
        this.participantA = participantA;
        this.participantB = participantB;
    }

    public Cat getParticipantA() {
        return this.participantA;
    }

    public Cat getParticipantB() {
        return this.participantB;
    }

    public Cat getWinner() {
        return this.winner;
    }

    public void catAWon() {
        this.winner = participantA;
    }

    public void catBWon() {
        this.winner = participantB;
    }
}
