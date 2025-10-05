package dots;

import game.Player;

public final class Edge {
    public enum Orient { H, V }
    private final int r, c;
    private final Orient orient;
    private Player owner; // null if free

    public Edge(int r, int c, Orient o) { this.r=r; this.c=c; this.orient=o; }
    public int r() { return r; }
    public int c() { return c; }
    public Orient orient() { return orient; }
    public boolean isFree() { return owner == null; }
    public Player owner() { return owner; }
    public void claim(Player p) {
        if (!isFree()) throw new IllegalStateException("Edge already claimed");
        owner = p;
    }
}
