/**
 * File: SlidingPuzzleBoard.java
 * Description: Concrete Board for N-by-M sliding puzzle. Encapsulates state, moves and shuffle.
 */
package puzzle;

import game.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidingPuzzleBoard extends Board {
    /** Upper bound for allowed board cells to avoid huge consoles / memory. */
    public static final int MAX_CELLS = 10_000;
    /** Minimal random walk steps added during reset to ensure sufficient scrambling. */
    public static final int RANDOM_MIN_STEPS = 7;

    private Tile[][] grid;
    private int emptyR, emptyC;
    private final Random rng = new Random();

    public SlidingPuzzleBoard(int rows, int cols) {
        super(rows, cols);
        if ((long) rows * cols > MAX_CELLS) throw new IllegalArgumentException("rows*cols too large");
        reset();
    }

    @Override
    public void reset() {
        // Build solved board
        grid = new Tile[rows][cols];
        int v = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == rows - 1 && c == cols - 1) {
                    grid[r][c] = new Tile(0);
                    emptyR = r; emptyC = c;
                } else {
                    grid[r][c] = new Tile(v++);
                }
            }
        }
        // Randomize via valid moves -> always solvable
        int steps = RANDOM_MIN_STEPS + Math.max(rows, cols) + rng.nextInt(rows * cols);
        randomWalk(steps);
    }

    /** Attempt to slide the tile with the given number into the empty cell if adjacent. */
    public boolean slideByNumber(int number) {
        int[] pos = find(number);
        if (pos == null) return false;
        int r = pos[0], c = pos[1];
        if (isNeighbor(r, c, emptyR, emptyC)) {
            swap(r, c, emptyR, emptyC);
            emptyR = r; emptyC = c;
            return true;
        }
        return false;
    }

    private int[] find(int value) {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c].getValue() == value) return new int[]{r, c};
        return null;
    }

    private boolean isNeighbor(int r1, int c1, int r2, int c2) {
        return (r1 == r2 && Math.abs(c1 - c2) == 1) || (c1 == c2 && Math.abs(r1 - r2) == 1);
    }

    private void swap(int r1, int c1, int r2, int c2) {
        Tile t = grid[r1][c1]; grid[r1][c1] = grid[r2][c2]; grid[r2][c2] = t;
    }

    /** Random walk of the empty tile to scramble while preserving solvability. */
    private void randomWalk(int steps) {
        for (int i = 0; i < steps; i++) {
            List<int[]> nbs = neighborsOfEmpty();
            int[] pick = nbs.get(rng.nextInt(nbs.size()));
            swap(emptyR, emptyC, pick[0], pick[1]);
            emptyR = pick[0]; emptyC = pick[1];
        }
    }

    private List<int[]> neighborsOfEmpty() {
        List<int[]> list = new ArrayList<>(4);
        if (emptyR > 0) list.add(new int[]{emptyR - 1, emptyC});
        if (emptyR < rows - 1) list.add(new int[]{emptyR + 1, emptyC});
        if (emptyC > 0) list.add(new int[]{emptyR, emptyC - 1});
        if (emptyC < cols - 1) list.add(new int[]{emptyR, emptyC + 1});
        return list;
    }

    @Override
    public boolean isSolved() {
        int expect = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int v = grid[r][c].getValue();
                if (r == rows - 1 && c == cols - 1) return v == 0;
                if (v != expect++) return false;
            }
        }
        return true;
    }

    @Override
    public String render() {
        // Text UI similar to your drawing.print()
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r <= rows; r++) {
            sb.append("+");
            for (int c = 0; c < cols; c++) sb.append("----+");
            sb.append("\n");
            if (r == rows) break;
            sb.append("|");
            for (int c = 0; c < cols; c++) sb.append(String.format("%-4s|", grid[r][c]));
            sb.append("\n");
        }
        return sb.toString();
    }
}
