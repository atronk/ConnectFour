package four;

import javax.swing.*;

public class JButton4 extends JButton {
    static int ROWS = 6;
    final private int r;
    final private int c;
    boolean win = false;

    JButton4(int r, int c) {
        super(" ");
        this.r = r;
        this.c = c;
        setButtonName();
    }

    int r() {
        return this.r;
    }

    int c() {
        return this.c;
    }

    char col() {
        return (char) ('A' + this.c);
    }

    int row() {
        return ROWS - this.r;
    }

    private void setButtonName() {
        this.setName("Button" + this.col() + this.row());
    }
}
