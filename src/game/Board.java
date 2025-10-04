/**
 * Course: CS-XXX — Sliding Puzzle Assignment
 * File: Board.java
 * Description: Abstract board surface; concrete games implement reset/render/solved.
 */
package game;

public abstract class Board {
    protected final int rows;
    protected final int cols;

    protected Board(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("rows/cols must be > 0");
        this.rows = rows; this.cols = cols;
    }

    public final int getRows() { return rows; }
    public final int getCols() { return cols; }

    /** Reset to initial (usually solved) state and then let the game randomize if needed. */
    public abstract void reset();

    /** @return textual representation for console UI. */
    public abstract String render();

    /** @return whether the board is currently solved. */
    public abstract boolean isSolved();
}
