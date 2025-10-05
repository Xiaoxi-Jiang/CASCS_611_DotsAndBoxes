package game;
public class ScoreBoard {
    private int p1Wins, p2Wins, ties;
    public void addResult(int cmp) { if (cmp>0) p1Wins++; else if (cmp<0) p2Wins++; else ties++; }
    public String summary(String p1, String p2) {
        return String.format("Scoreboard — %s: %d, %s: %d, Ties: %d", p1, p2, p2Wins, p1Wins, ties);
    }
}
