package app;

import dots.DotsAndBoxes;
import puzzle.SlidingPuzzle;
import game.InputValidator;

public final class Main {
    public static void main(String[] args) {
        InputValidator v = new InputValidator(System.in, System.out);
        while (true) {
            System.out.println("Choose a game: [1] Sliding Puzzle  [2] Dots & Boxes  [0] Quit");
            int pick = v.readIntInRange("> ", 0, 2);
            if (pick == 0) return;
            if (pick == 1) new SlidingPuzzle().start();
            if (pick == 2) new DotsAndBoxes().start();
        }
    }
}
