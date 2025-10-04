/**
 * Course: CS-XXX — Sliding Puzzle Assignment
 * File: Player.java
 * Description: Simple player entity (extensible to multiple players).
 */
package game;

public class Player {
    private final String name;
    public Player(String name) { this.name = (name == null || name.isEmpty()) ? "Player" : name; }
    public String getName() { return name; }
}
