/**
 * File: Piece.java
 * Description: Abstract game piece; specialized by Tile for sliding puzzle.
 */
package puzzle;

public abstract class Piece {
    /** @return true if this logical piece represents an empty space. */
    public abstract boolean isEmpty();
}
