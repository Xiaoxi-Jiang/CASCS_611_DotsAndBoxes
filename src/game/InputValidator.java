package game;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;
    private final PrintStream out;

    public InputValidator(InputStream in, PrintStream out) {
        this.sc = new Scanner(in).useLocale(Locale.US);
        this.out = out;
    }

    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            out.print(prompt);
            try {
                int v = sc.nextInt();
                if (v < min || v > max) { out.println("Please enter an integer in ["+min+", "+max+"]."); continue; }
                return v;
            } catch (InputMismatchException e) {
                out.println("Invalid input! Please enter an integer.");
                sc.nextLine();
            }
        }
    }

    public String readToken(String prompt) {
        out.print(prompt);
        return sc.next();
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            out.print(prompt);
            String t = sc.next().trim().toLowerCase();
            if (t.equals("y") || t.equals("yes")) return true;
            if (t.equals("n") || t.equals("no")) return false;
            out.println("Please enter y/n.");
        }
    }
}
