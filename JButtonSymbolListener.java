package four;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonSymbolListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton4 b = (JButton4) e.getSource();
        JButton4 toMark = ConnectFour.getLastButtonOrNull(b.c());
        if (toMark != null) {
            ConnectFour.markColumn(toMark.c());
            ConnectFour.check(toMark);
        }
    }
}
