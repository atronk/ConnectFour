package four;

import java.util.HashSet;
import java.util.Set;

public class ConnectFourChecker {
    static int ROWS;
    static int COLS;
    final private JButton4[][] field;
    int[][] moves = new int[][]{
            {0, 1, 2, 3},
            {-1, 0, 1, 2},
            {-2, -1, 0, 1},
            {-3, -2, -1, 0}
    };

    ConnectFourChecker(JButton4[][] field) {
        this.field = field;
    }

    private Set<int[]> checkXY(int r, int c, boolean vertical) {
        Set<int[]> setOfMoves = new HashSet<>();
        String sym = field[r][c].getText();

        for (int[] move : moves) {
            for (int delta : move) {
                int dr = vertical ? r + delta : r;
                int dc = vertical ? c : c + delta;

                if (dr >= 0 && dc >= 0 && dr < ROWS && dc < COLS &&
                        field[dr][dc].getText().equals(sym)) {
                    setOfMoves.add(new int[]{dr, dc});
                } else {
                    break;
                }
            }

            if (setOfMoves.size() == 4) {
                break;
            } else {
                setOfMoves.clear();
            }
        }
        return setOfMoves.size() == 0 ? null : setOfMoves;
    }

    private Set<int[]> checkDiagonal(int r, int c, boolean rising) {
        Set<int[]> setOfMoves = new HashSet<>();
        String sym = field[r][c].getText();

        for (int[] move : moves) {
            for (int delta : move) {
                int dr = rising ? r - delta : r + delta;
                int dc = c + delta;

                if (dr >= 0 && dc >= 0 && dr < ROWS && dc < COLS &&
                        field[dr][dc].getText().equals(sym)) {
                    setOfMoves.add(new int[]{dr, dc});
                } else {
                    break;
                }
            }

            if (setOfMoves.size() == 4) {
                break;
            } else {
                setOfMoves.clear();
            }
        }
        return setOfMoves.size() == 0 ? null : setOfMoves;
    }

    Set<int[]> getSetOrNull(int r, int c, FunctionChecker[] funcs) {
        for (FunctionChecker f : funcs) {
            for (Boolean bool : Set.of(true, false)) {
                Set<int[]> set = f.apply(r, c, bool);
                if (set != null) {
                    return set;
                }
            }
        }
        return null;
    }

    Set<int[]> check(int r, int c) {
        FunctionChecker[] funcs = new FunctionChecker[]{this::checkXY, this::checkDiagonal};
        return getSetOrNull(r, c, funcs);
    }

    @FunctionalInterface
    interface FunctionChecker {
        Set<int[]> apply(int r, int c, boolean b);
    }
}
