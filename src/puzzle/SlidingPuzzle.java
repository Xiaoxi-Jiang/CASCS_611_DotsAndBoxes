/**
 * File: SlidingPuzzle.java
 * Description: Concrete Game for Sliding Puzzle; concise main and full comments.
 */
package puzzle;

import game.Game;
import game.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SlidingPuzzle extends Game {
    private final Scanner sc = new Scanner(System.in);
    private Player player;

    /** Concise main: no game logic here. */
    public static void main(String[] args) {
        new SlidingPuzzle().start();
    }

    /** Starts UI loop, validates inputs, and delegates moves to the Board. */
    @Override
    public void start() {
        System.out.println("Hello! Type 'default' for 3x3, or 'set' to choose size.");
        int rows, cols;

        while (true) {
            String mode = sc.next();
            if ("default".equalsIgnoreCase(mode)) { rows = cols = 3; break; }
            if ("set".equalsIgnoreCase(mode)) {
                rows = readPositiveInt("Line number is: ");
                cols = readPositiveInt("Row  number is: ");
                if (1L * rows * cols <= SlidingPuzzleBoard.MAX_CELLS) break;
                System.out.println("Invalid input! line*row must be <= " + SlidingPuzzleBoard.MAX_CELLS);
            } else {
                System.out.println("Invalid input! Type 'default' or 'set'.");
            }
        }

        this.board = new SlidingPuzzleBoard(rows, cols);
        this.player = new Player("Player");

        // Main loop: render → read → attempt move → repeat until solved
        while (!isOver()) {
            System.out.println(board.render());
            int n = readPositiveInt("Which tile do you want to slide into the empty space?\n");
            boolean ok = ((SlidingPuzzleBoard) board).slideByNumber(n);
            if (!ok) System.out.println("Input " + n + " is not adjacent to empty. Try again.");
        }

        System.out.println(board.render());
        System.out.println("Congratulations, " + player.getName() + "! You win!");
    }

    @Override
    public boolean isOver() { return board.isSolved(); }

    /** Read a positive integer from stdin with robust error handling. */
    private int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = sc.nextInt();
                if (v > 0) return v;
                System.out.println("Please enter an integer > 0.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer!");
                sc.nextLine(); // clear buffer
            }
        }
    }
}
