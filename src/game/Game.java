/**
 * Course: CS-XXX — Sliding Puzzle Assignment
 * File: Game.java
 * Description: Abstract game controller defining lifecycle for any board game.
 * Author: Your Name
 * Date: 2025-10-04
 */
package game;

public abstract class Game {
    protected Board board;

    /** Starts the game loop and manages user interaction. */
    public abstract void start();

    /** @return true if the game has reached a terminal state. */
    public abstract boolean isOver();
}
