public class Score {
    private int playerOneScore;
    private int playerTwoScore;


    public Score(int playerOneScore, int playerTwoScore) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void incrementPlayerOneScore() {
        playerOneScore = playerOneScore + 1;
    }

    public void incrementPlayerTwoScore() {
        playerTwoScore = playerTwoScore + 1;
    }

    /*if (playerOneScore == 11 || playerTwoScore == 11) {
        //end game

    }*/
}
