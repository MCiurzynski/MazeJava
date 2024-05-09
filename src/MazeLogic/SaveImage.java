/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import MazeGUI.MazePanel;
import MazeGUI.Properties;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author golde
 */
public class SaveImage {
    public static void saveImage(Maze m, File file) {
        System.out.println(file.getPath());
        if (!file.getPath().endsWith(".png")) {
            file = new File(file.getPath() + ".png");
        }
        if (file.exists()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Podany plik istnieje. Chcesz go nadpisać?");
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
        }
        ImageMaker im = new ImageMaker(m, file);
        im.start();
    }

    private static class ImageMaker extends Thread {

        private Maze m;
        private File file;

        ImageMaker(Maze m, File file) {
            this.m = m;
            this.file = file;
        }

        @Override
        public void run() {
            int tmp = Properties.getCellSize();
            Properties.setCellSize(10);
            MazePanel mazePanel = new MazePanel(m);
            mazePanel.setSize(m.getCol() * Properties.getCellSize(), m.getRow() * Properties.getCellSize());
            BufferedImage image = new BufferedImage(m.getCol() * Properties.getCellSize(), m.getRow() * Properties.getCellSize(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            mazePanel.paint(g);
            Properties.setCellSize(tmp);
            g.dispose();
            try {
                ImageIO.write(image, "png", new File(file.getPath()));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Nie udało zapisać się pliku");
            }
        }
    }
}
