/**
 * File: Tile.java
 * Description: Numbered tile (0 denotes the empty space).
 */
package puzzle;

public class Tile extends Piece {
    private final int value; // 0 -> empty
    public Tile(int value) { this.value = value; }
    public int getValue() { return value; }
    @Override public boolean isEmpty() { return value == 0; }
    @Override public String toString() { return isEmpty() ? " " : Integer.toString(value); }
}
