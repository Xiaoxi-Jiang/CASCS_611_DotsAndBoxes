package dots;

import game.Player;

public final class Box {
    private final Edge top, right, bottom, left;
    private Player owner;

    public Box(Edge top, Edge right, Edge bottom, Edge left) {
        this.top=top; this.right=right; this.bottom=bottom; this.left=left;
    }
    public boolean isClosed() {
        return top.owner()!=null && right.owner()!=null && bottom.owner()!=null && left.owner()!=null;
    }
    public boolean hasOwner() { return owner != null; }
    public Player owner() { return owner; }
    public boolean assignIfClosed(Player p) {
        if (!hasOwner() && isClosed()) { owner = p; return true; }
        return false;
    }

    public void reset() {
        owner = null;
    }
}
