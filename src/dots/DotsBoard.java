package dots;

import game.Board;

public final class DotsBoard extends Board {
    private final Edge[][] horiz; // rows x (cols-1)
    private final Edge[][] vert;  // (rows-1) x cols
    private final Box[][] boxes;  // (rows-1) x (cols-1)

    public DotsBoard(int rows, int cols) {
        super(rows, cols);
        if (rows < 2 || cols < 2) throw new IllegalArgumentException("Need at least 2x2 dots");
        horiz = new Edge[rows][cols-1];
        vert  = new Edge[rows-1][cols];
        for (int r=0;r<rows;r++) for (int c=0;c<cols-1;c++) horiz[r][c] = new Edge(r,c, Edge.Orient.H);
        for (int r=0;r<rows-1;r++) for (int c=0;c<cols;c++) vert[r][c]  = new Edge(r,c, Edge.Orient.V);
        boxes = new Box[rows-1][cols-1];
        for (int r=0;r<rows-1;r++) for (int c=0;c<cols-1;c++)
            boxes[r][c] = new Box(horiz[r][c], vert[r][c+1], horiz[r+1][c], vert[r][c]);
    }

    @Override public void reset() {
        for (Edge[] row : horiz) {
            for (Edge edge : row) {
                edge.reset();
            }
        }
        for (Edge[] row : vert) {
            for (Edge edge : row) {
                edge.reset();
            }
        }
        for (Box[] row : boxes) {
            for (Box box : row) {
                box.reset();
            }
        }
    }

    public Edge getEdge(int r, int c, Edge.Orient o) {
        return (o==Edge.Orient.H) ? horiz[r][c] : vert[r][c];
    }

    public Box getBox(int r, int c) { return boxes[r][c]; }

    /** Claims an edge; returns number of boxes scored (0/1/2). */
    public int claimAndScore(Edge e, game.Player p) {
        e.claim(p);
        int scored = 0;
        // Up to two adjacent boxes:
        if (e.orient()==Edge.Orient.H) {
            if (e.r()>0) if (boxes[e.r()-1][e.c()].assignIfClosed(p)) scored++;
            if (e.r()<rows-1) if (boxes[e.r()][e.c()].assignIfClosed(p)) scored++;
        } else {
            if (e.c()>0) if (boxes[e.r()][e.c()-1].assignIfClosed(p)) scored++;
            if (e.c()<cols-1) if (boxes[e.r()][e.c()].assignIfClosed(p)) scored++;
        }
        return scored;
    }

    public boolean allBoxesClaimed() {
        for (int r=0;r<rows-1;r++)
            for (int c=0;c<cols-1;c++)
                if (!boxes[r][c].hasOwner())
                    return false;
        return true;
    }

    @Override public boolean isSolved() { return allBoxesClaimed(); }

    @Override public String render() {
        // Simple ASCII: dots, --- for H edges, | for V edges, box owner initials
        StringBuilder sb = new StringBuilder();
        for (int r=0;r<rows;r++) {
            // dots + horizontal edges
            for (int c=0;c<cols;c++) {
                sb.append("•");
                if (c<cols-1) sb.append(horiz[r][c].isFree() ? "   " : "---");
            }
            sb.append("\n");
            if (r<rows-1) {
                // vertical edges + box owners
                for (int c=0;c<cols;c++) {
                    sb.append(vert[r][c].isFree() ? " " : "|");
                    if (c<cols-1) {
                        Box b = boxes[r][c];
                        String mid = b.hasOwner() ? (" " + b.owner().getName().charAt(0) + " ") : "   ";
                        sb.append(mid);
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
