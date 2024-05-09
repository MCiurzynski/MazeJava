package MazeGUI;

import MazeLogic.Maze;
import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private Maze m;

    public MazePanel(Maze m) {
        this.m = m;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = m.getCol() * m.getRow();
        for (int i = 0; i < size; i++) {
            switch (m.getBoard(i)) {
                case 'X':
                    g.setColor(Properties.getWallColor());
                    break;
                case ' ':
                    g.setColor(Properties.getRoomColor());
                    break;
                case 'S':
                    g.setColor(Properties.getPathColor());
                    break;
            }
            g.fillRect(i % m.getRow() * Properties.getCellSize(), i / m.getRow() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
        }
        g.setColor(Properties.getStartColor());
        g.fillRect(m.getStart() % m.getRow() * Properties.getCellSize(), m.getStart() / m.getRow() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
        g.setColor(Properties.getEndColor());
        g.fillRect(m.getEnd() % m.getRow() * Properties.getCellSize(), m.getEnd() / m.getRow() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
    }
}
