package game;
import java.io.PrintStream;
public class TextRenderer implements Renderer {
    private final PrintStream out;
    public TextRenderer(PrintStream out) { this.out = out; }
    public void print(String s) { out.print(s); }
    public void println(String s) { out.println(s); }
}