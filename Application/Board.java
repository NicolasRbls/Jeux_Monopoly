import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private List<Case> cases;
    public static final int NUM_CASES = 40;

    public Board() {
        cases = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < NUM_CASES; i++) {
            addCase(new Case("Case " + (i + 1)));
        }
    }

    public List<Case> getCases() {
        return cases;
    }

    public void addCase(Case aCase) {
        cases.add(aCase);
    }
}