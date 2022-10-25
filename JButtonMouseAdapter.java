package four;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonMouseAdapter extends MouseAdapter implements Colors4 {
    private static void setColor(JButton4 btn, Color winner, Color base) {
        if (btn.win) {
            btn.setBackground(winner);
        } else {
            btn.setBackground(base);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton4 btn = (JButton4) e.getSource();
        setColor(btn, WINNER_HOVER, BASE_HOVER);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton4 btn = (JButton4) e.getSource();
        setColor(btn, WINNER_PLAIN, BASE_PLAIN);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton4 btn = (JButton4) e.getSource();
        setColor(btn, WINNER_PRESS, BASE_PRESS);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton4 btn = (JButton4) e.getSource();
        setColor(btn, WINNER_HOVER, BASE_HOVER);
    }
}
