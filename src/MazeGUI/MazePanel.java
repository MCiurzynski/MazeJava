package MazeGUI;

import MazeLogic.Properties;
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
        int size = m.getRow() * m.getCol();
        for (int i = 0; i < size; i++) {
            switch (m.getBoard(i)) {
                case 'X':
                    g.setColor(Properties.getWallColor());
                    break;
                case ' ':
                    g.setColor(Properties.getRoomColor());
                    break;
            }
            g.fillRect(i % m.getCol() * Properties.getCellSize(), i / m.getCol() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
        }
        g.setColor(Properties.getStartColor());
        g.fillRect(m.getStart() % m.getCol() * Properties.getCellSize(), m.getStart() / m.getCol() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
        g.setColor(Properties.getEndColor());
        g.fillRect(m.getEnd() % m.getCol() * Properties.getCellSize(), m.getEnd() / m.getCol() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
        if (m.getPathLength() != -1) {
            for (int i = 0; i < m.getPathLength(); i++) {
                g.setColor(Properties.getPathColor());
                g.fillRect(m.getPath(i) % m.getCol() * Properties.getCellSize(), m.getPath(i) / m.getCol() * Properties.getCellSize(), Properties.getCellSize(), Properties.getCellSize());
            }
        }
    }
}
