/**
 * Course: CS-XXX — Sliding Puzzle Assignment
 * File: Player.java
 * Description: Simple player entity (extensible to multiple players).
 */
package game;

public class Player {
    private final String name;
    private int score = 0;
    public Player(String name) { this.name = (name == null || name.isEmpty()) ? "Player" : name; }
    public String getName() { return name; }
    public int score() { return score; }
    public void addScore(int delta) { score += delta; }
    public void resetScore() { score = 0; }
}
