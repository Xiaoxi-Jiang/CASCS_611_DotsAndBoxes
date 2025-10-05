package dots;

public final class RulesDots {
    public static boolean isLegal(DotsBoard b, DotsMove m) {
        if (m.orient()==Edge.Orient.H) {
            if (m.r()<0 || m.r()>=b.getRows() || m.c()<0 || m.c()>=b.getCols()-1) return false;
        } else {
            if (m.r()<0 || m.r()>=b.getRows()-1 || m.c()<0 || m.c()>=b.getCols()) return false;
        }
        return b.getEdge(m.r(), m.c(), m.orient()).isFree();
    }
}
