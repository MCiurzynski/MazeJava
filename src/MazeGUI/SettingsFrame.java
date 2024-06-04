/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeGUI;

/**
 *
 * @author golde
 */
import MazeLogic.Properties;
import java.awt.*;
import javax.swing.*;

public class SettingsFrame extends JFrame {

    private MazeFrame mazeFrame;
    
    final private FlowLayout basicLayout;
    final private FlowLayout applyLayout;

    final private JLabel cellSizeLabel;
    final private SpinnerModel cellSizeSpinnerModel;
    final private JSpinner cellSizeSpinner;
    final private JPanel cellSizePanel;

    final private JLabel roomColorLabel;
    final private JButton roomColorButton;
    final private JPanel roomColorPanel;
    private Color newRoomColor;

    final private JLabel wallColorLabel;
    final private JButton wallColorButton;
    final private JPanel wallColorPanel;
    private Color newWallColor;

    final private JLabel pathColorLabel;
    final private JButton pathColorButton;
    final private JPanel pathColorPanel;
    private Color newPathColor;

    final private JLabel startColorLabel;
    final private JButton startColorButton;
    final private JPanel startColorPanel;
    private Color newStartColor;

    final private JLabel endColorLabel;
    final private JButton endColorButton;
    private Color newEndColor;
    final private JPanel endColorPanel;

    final private JButton apply;
    final private JButton cancel;
    final private JPanel applyPanel;

    SettingsFrame(MazeFrame mazeFrame) {
        super("Ustawienia");
        this.mazeFrame = mazeFrame;

        UIManager.put("TextComponent.arc", 5);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setResizable(false);

        basicLayout = new FlowLayout();
        basicLayout.setAlignment(FlowLayout.LEFT);
        applyLayout = new FlowLayout();
        applyLayout.setAlignment(FlowLayout.RIGHT);
        setLayout(new GridLayout(7, 1));

        cellSizeLabel = new JLabel("Rozmiar komórki labiryntu");
        cellSizeSpinnerModel = new SpinnerNumberModel(Properties.getCellSize(), 1, 100, 1);
        cellSizeSpinner = new JSpinner(cellSizeSpinnerModel);
        cellSizeSpinner.setPreferredSize(new Dimension(72, 22));
        cellSizePanel = new JPanel(basicLayout);
        cellSizePanel.add(cellSizeSpinner);
        cellSizePanel.add(cellSizeLabel);
        add(cellSizePanel);

        roomColorLabel = new JLabel("Kolor tła");
        roomColorButton = new JButton("Wybierz");
        roomColorButton.addActionListener((e) -> {
            newRoomColor = JColorChooser.showDialog(null , "Wybierz kolor tła", Properties.getRoomColor());
        });
        roomColorPanel = new JPanel(basicLayout);
        roomColorPanel.add(roomColorButton);
        roomColorPanel.add(roomColorLabel);

        add(roomColorPanel);

        wallColorLabel = new JLabel("Kolor ściany");
        wallColorButton = new JButton("Wybierz");
        wallColorButton.addActionListener((e) -> {
            newWallColor = JColorChooser.showDialog(null , "Wybierz kolor ściany", Properties.getWallColor());
        });
        wallColorPanel = new JPanel(basicLayout);
        wallColorPanel.add(wallColorButton);
        wallColorPanel.add(wallColorLabel);
        add(wallColorPanel);

        pathColorLabel = new JLabel("Kolor ścieżki");
        pathColorButton = new JButton("Wybierz");
        pathColorButton.addActionListener((e) -> {
            newPathColor = JColorChooser.showDialog(null , "Wybierz kolor ścieżki", Properties.getPathColor());
        });
        pathColorPanel = new JPanel(basicLayout);
        pathColorPanel.add(pathColorButton);
        pathColorPanel.add(pathColorLabel);
        add(pathColorPanel);

        startColorLabel = new JLabel("Kolor początku");
        startColorButton = new JButton("Wybierz");
        startColorButton.addActionListener((e) -> {
            newStartColor = JColorChooser.showDialog(null , "Wybierz kolor początku", Properties.getStartColor());
        });
        startColorPanel = new JPanel(basicLayout);
        startColorPanel.add(startColorButton);
        startColorPanel.add(startColorLabel);
        add(startColorPanel);

        endColorLabel = new JLabel("Kolor końca");
        endColorButton = new JButton("Wybierz");
        endColorButton.addActionListener((e) -> {
            newEndColor = JColorChooser.showDialog(null , "Wybierz kolor końca", Properties.getEndColor());
        });
        endColorPanel = new JPanel(basicLayout);
        endColorPanel.add(endColorButton);
        endColorPanel.add(endColorLabel);
        add(endColorPanel);

        apply = new JButton("Zastosuj");
        apply.addActionListener((e) -> {
            if (newRoomColor != null)
                Properties.setRoomColor(newRoomColor);
            if (newWallColor != null)
                Properties.setWallColor(newWallColor);
            if (newPathColor != null)
                Properties.setPathColor(newPathColor);
            if (newStartColor != null)
                Properties.setStartColor(newStartColor);
            if (newEndColor != null)
                Properties.setEndColor(newEndColor);
            int cellSize;
            cellSize = (int) cellSizeSpinner.getValue();
            Properties.setCellSize(cellSize);
            mazeFrame.resetMaze();
            setVisible(false);
        });
        cancel = new JButton("Anuluj");
        cancel.addActionListener((e) -> {
            setVisible(false);
        });
        applyPanel = new JPanel(applyLayout);
        applyPanel.add(apply);
        applyPanel.add(cancel);
        add(applyPanel);

        setVisible(true);

    }
}
