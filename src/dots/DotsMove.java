package dots;

import game.Move;
import game.Player;

public final class DotsMove extends Move {
    private final int r, c;
    private final Edge.Orient orient;
    public DotsMove(Player p, int r, int c, Edge.Orient o) { super(p); this.r=r; this.c=c; this.orient=o; }
    public int r() { return r; }
    public int c() { return c; }
    public Edge.Orient orient() { return orient; }
}
