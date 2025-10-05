package dots;

import game.Game;
import game.Player;
import game.InputValidator;

public class DotsAndBoxes extends Game {
    private Player p1, p2, current;
    private InputValidator v = new InputValidator(System.in, System.out);

    @Override
    public void start() {
        System.out.println("Dots & Boxes — enter number of dots (rows, cols >= 2).");
        int rows = v.readIntInRange("Rows (dots): ", 2, 30);
        int cols = v.readIntInRange("Cols (dots): ", 2, 30);
        board = new DotsBoard(rows, cols);
        p1 = new Player(promptName("Player 1 name: "));
        p2 = new Player(promptName("Player 2 name: "));
        current = p1;

        System.out.println("Input format: H r c  (horizontal edge at row r, col c)");
        System.out.println("              V r c  (vertical   edge at row r, col c)");
        System.out.println("Type: help  to reprint instructions;  quit  to exit game.\n");

        while (!isOver()) {
            System.out.println(board.render());
            String t = v.readToken(current.getName()+" move (H/V r c): ").trim();
            if (t.equalsIgnoreCase("help")) { printHelp(); continue; }
            if (t.equalsIgnoreCase("quit")) { System.out.println("Quitting game."); return; }

            char o = Character.toUpperCase(t.charAt(0));
            int r = v.readIntInRange("", 0, (o=='H'? rows-1 : rows-2));  // inclusive upper bound will be validated
            int c = v.readIntInRange("", 0, (o=='H'? cols-2 : cols-1));

            Edge.Orient orient = (o=='H') ? Edge.Orient.H : Edge.Orient.V;
            DotsMove m = new DotsMove(current, r, c, orient);

            if (!RulesDots.isLegal((DotsBoard)board, m)) {
                System.out.println("Illegal move: out of range or already claimed. Try again.\n");
                continue;
            }
            int scored = ((DotsBoard)board).claimAndScore(((DotsBoard)board).getEdge(r,c,orient), current);
            if (scored > 0) {
                current.addScore(scored);
                System.out.println(current.getName()+" scored "+scored+" box(es)! Take another turn.");
            } else {
                current = (current == p1 ? p2 : p1);
            }
        }

        System.out.println(board.render());
        int cmp = Integer.compare(p1.score(), p2.score());
        if (cmp > 0) System.out.println("Winner: "+p1.getName()+" ("+p1.score()+"-"+p2.score()+")");
        else if (cmp < 0) System.out.println("Winner: "+p2.getName()+" ("+p2.score()+"-"+p1.score()+")");
        else System.out.println("Tie game ("+p1.score()+"-"+p2.score()+")");
    }

    @Override public boolean isOver() { return board.isSolved(); }

    private String promptName(String prompt) {
        String name = v.readToken(prompt).trim();
        return name.isEmpty()? "Player": name;
    }

    private void printHelp() {
        System.out.println("Dots & Boxes rules: Players alternate claiming edges. "
                + "Completing a 1x1 box scores 1 point and grants an extra turn. "
                + "Game ends when all boxes are claimed. Highest score wins.");
        System.out.println("Example moves:  H 0 0   V 1 2");
        System.out.println("H r c valid ranges: 0 <= r < rows, 0 <= c < cols-1");
        System.out.println("V r c valid ranges: 0 <= r < rows-1, 0 <= c < cols");
    }
}
