package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Set;

public class ConnectFour extends JFrame implements Colors4 {
    final private static int ROWS = 6;
    final private static int COLS = 7;
    final private static JButton4[][] buttons = new JButton4[ROWS][COLS];
    final private static ConnectFourChecker checker = new ConnectFourChecker(buttons);
    final private static ActionListener actionListener = new JButtonSymbolListener();
    final private static MouseAdapter mouseAdapter = new JButtonMouseAdapter();
    private static char SYM = 'X';

    public ConnectFour() {
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLocationRelativeTo(null);
        JButton4.ROWS = ROWS;
        ConnectFourChecker.ROWS = ROWS;
        ConnectFourChecker.COLS = COLS;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel fieldPanel = getFieldPanel();
        JPanel bottomPanel = getBottomPanel();

        mainPanel.add(fieldPanel);
        mainPanel.add(bottomPanel);
        mainPanel.setVisible(true);

        add(mainPanel);
        setVisible(true);
    }

    private static void resetField() {
        SYM = 'X';
        boolean hasListener = buttons[0][0].getActionListeners().length != 0;
        for (JButton4[] row : buttons) {
            for (JButton4 btn : row) {
                btn.setBackground(BASE_PLAIN);
                btn.setText(" ");
                if (hasListener) {
                    btn.removeActionListener(actionListener);
                }
                btn.addActionListener(actionListener);
                btn.win = false;
            }
        }
    }

    static void switchSym() {
        SYM = SYM == 'X' ? 'O' : 'X';
    }

    static JButton4 getLastButtonOrNull(int col) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (buttons[r][col].getText().equals(" ")) {
                return buttons[r][col];
            }
        }
        return null;
    }

    static void markColumn(int col) {
        JButton btn = getLastButtonOrNull(col);

        if (btn != null) {
            btn.setText(String.valueOf(SYM));
            switchSym();
        }
    }

    static void check(JButton4 buttonToCheck) {
        Set<int[]> winFields = checker.check(buttonToCheck.r(), buttonToCheck.c());
        if (winFields != null) {
            for (int[] field : winFields) {
                int r = field[0];
                int c = field[1];
                buttons[r][c].win = true;
                buttons[r][c].setBackground(WINNER_PLAIN);
            }
            // disable buttons
            for (JButton4[] row : buttons) {
                for (JButton4 button : row) {
                    button.removeActionListener(actionListener);
                }
            }
        }
    }

    private JButton4 createButton(int r, int c) {
        JButton4 b = new JButton4(r, c);
        b.setFocusPainted(false);
        b.setBackground(BASE_PLAIN);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setBorder(null);
        b.addMouseListener(mouseAdapter);
        b.addActionListener(actionListener);
        return b;
    }

    private JPanel getFieldPanel() {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(ROWS, COLS, 5, 5));
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                JButton4 b = createButton(r, c);
                fieldPanel.add(b);
                buttons[r][c] = b;
            }
        }
        fieldPanel.setVisible(true);
        return fieldPanel;
    }

    private JButton getResetButton() {
        JButton btn = new JButton("Reset");

        btn.setName("ButtonReset");
        btn.addActionListener(e -> resetField());
        return btn;
    }

    private JPanel getBottomPanel() {
        JPanel panel = new JPanel();
        JButton btn = getResetButton();

        panel.setMaximumSize(new Dimension(400, 20));
        panel.setLayout(new BorderLayout());
        panel.add(btn, BorderLayout.NORTH);
        panel.setVisible(true);

        return panel;
    }
}