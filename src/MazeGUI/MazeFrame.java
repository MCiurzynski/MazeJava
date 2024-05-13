/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeGUI;

import MazeLogic.FileNotCorrectException;
import MazeLogic.Properties;
import MazeLogic.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author golde
 */
public class MazeFrame extends JFrame {

    private MazeFrame mazeFrame;
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem readTxtFile;
    private JMenuItem readBinFile;
    private JMenuItem saveMazeImage;
    private JMenuItem compressMaze;
    private JMenuItem saveMaze;
    private JMenuItem settings;
    private JMenuItem help;
    private JScrollPane scrollMaze;
    private JButton findPath;
    private MazePanel mazePanel;
    private JPanel buttonPanel;
    private JFileChooser fileChooser;
    private Maze maze;
    private MazeReader mazeReader;

    public MazeFrame() {
        super("aMAZEing solver");
        mazeFrame = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setMinimumSize(new Dimension(400, 400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        maze = null;
        scrollMaze = null;

        setMenu();

        findPath = new JButton("findPath");
        findPath.setText("Znajdz ścieżke");
        findPath.addActionListener((e) -> {
            if (maze != null) {
                var findPath = new FindPath(maze);
                findPath.find();
                resetMaze();
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(findPath);
        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void createScrollMaze() {
        maze = new Maze();
        if (scrollMaze != null) {
            remove(scrollMaze);
            validate();
            repaint();
        }
        try {
            mazeReader.read(maze);
        } catch (FileNotCorrectException ex) {
            setMenuState(false);
            maze = null;
            JOptionPane.showMessageDialog(null, "Wybrany plik jest niepoprawny: " + ex);
            return;
        }
        mazePanel = new MazePanel(maze);
        scrollMaze = new JScrollPane(mazePanel);
        scrollMaze.setWheelScrollingEnabled(false);
        mazePanel.setPreferredSize(new Dimension(maze.getCol() * Properties.getCellSize(), maze.getRow() * Properties.getCellSize()));
        mazePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int cellX = e.getX() / Properties.getCellSize();
                int cellY = e.getY() / Properties.getCellSize();
                if (e.getButton() == 1) {
                    if (maze.getBoard(cellX + cellY * maze.getCol()) != 'X' && maze.getEnd() != cellX + cellY * maze.getCol()) {
                        maze.setStart(cellX + cellY * maze.getCol());
                        maze.clearPath();
                    }
                }
                if (e.getButton() == 3) {
                    if (maze.getBoard(cellX + cellY * maze.getCol()) != 'X' && maze.getStart() != cellX + cellY * maze.getCol()) {
                        maze.setEnd(cellX + cellY * maze.getCol());
                        maze.clearPath();
                    }
                }
                resetMaze();
            }
        });
        add(scrollMaze, BorderLayout.CENTER);
        validate();
        repaint();
        setMenuState(true);
    }

    public void resetMaze() {
        if (maze != null) {
            mazePanel.setPreferredSize(new Dimension(maze.getCol() * Properties.getCellSize(), maze.getRow() * Properties.getCellSize()));
            add(scrollMaze, BorderLayout.CENTER);
            validate();
            repaint();
            setMenuState(true);
        }
    }

    private void setMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        readTxtFile = new JMenuItem("readTxtFile");
        readTxtFile.setText("Wczytaj plik tekstowy");
        readTxtFile.addActionListener((e) -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Otwórz plik tekstowy");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe [.txt]", "txt"));
            int chooserVal = fileChooser.showOpenDialog(null);

            if (chooserVal == JFileChooser.APPROVE_OPTION) {
                File mazeFile = fileChooser.getSelectedFile();
                mazeReader = new MazeReader(mazeFile.getPath());
                createScrollMaze();
            }
        });
        menu.add(readTxtFile);

        readBinFile = new JMenuItem("readBinFile");
        readBinFile.setText("Wczytaj plik binarny");
        readBinFile.addActionListener((e) -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Otwórz plik binarny");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki binarne [.bin]", "bin"));
            int chooserVal = fileChooser.showOpenDialog(null);
            if (chooserVal == JFileChooser.APPROVE_OPTION) {
                File mazeFile = fileChooser.getSelectedFile();
                mazeReader = new BinaryMazeReader(mazeFile.getPath());
                createScrollMaze();
            }
        });
        menu.add(readBinFile);

        menu.addSeparator();

        saveMazeImage = new JMenuItem("saveMazeImage");
        saveMazeImage.setText("Zapisz labirynt");
        saveMazeImage.setEnabled(false);
        saveMazeImage.addActionListener((e) -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Zapisz labirynt");
            fileChooser.setFileFilter(new FileNameExtensionFilter(".png(*.png)", "png"));
            int chooserVal = fileChooser.showSaveDialog(null);
            if (chooserVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                SaveImage.saveImage(maze, fileToSave);
            }
        });
        menu.add(saveMazeImage);

        saveMaze = new JMenuItem("saveMaze");
        saveMaze.setText("Zapisz dane");
        saveMaze.addActionListener((e) -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Zapisz dane");
            fileChooser.setFileFilter(new FileNameExtensionFilter(".txt(*.txt)", "txt"));
            int chooserVal = fileChooser.showSaveDialog(null);
            if (chooserVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave .getPath().endsWith(".txt")) {
                    fileToSave  = new File(fileToSave .getPath() + ".txt");
                }
                if (fileToSave.exists()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Podany plik istnieje. Chcesz go nadpisać?");
                    if (dialogResult == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                try (var file = new FileWriter(fileToSave)) {
                    for (int i = 0; i < maze.getRow() * maze.getCol(); i++) {
                        if (i == maze.getStart())
                            file.write('P');
                        else if (i == maze.getEnd())
                            file.write('K');
                        else
                            file.write(maze.getBoard(i));
                        if (i % maze.getCol() == maze.getCol() - 1)
                            file.write('\n');
                    }
                }
                catch(IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        saveMaze.setEnabled(false);
        menu.add(saveMaze);

        compressMaze = new JMenuItem("compressMaze");
        compressMaze.setText("Kompresuj dane");
        compressMaze.setEnabled(false);
        menu.add(compressMaze);

        menu.addSeparator();

        settings = new JMenuItem("settings");
        settings.setText("Ustawienia");
        settings.addActionListener((e) -> {
            new SettingsFrame(mazeFrame);
        });
        menu.add(settings);

        menu.addSeparator();

        help = new JMenuItem("help");
        help.setText("Pomoc");
        menu.add(help);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void setMenuState(boolean b) {
        saveMazeImage.setEnabled(b);
        compressMaze.setEnabled(b);
        saveMaze.setEnabled(b);
    }
}
