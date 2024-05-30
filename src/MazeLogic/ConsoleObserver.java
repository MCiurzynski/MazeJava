/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import MazeGUI.MazeFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author golde
 */
public class ConsoleObserver extends Thread {

    ArrayList<MazeFrame> list;

    public ConsoleObserver() {
        super();
        setDaemon(true);
        list = new ArrayList<>();
    }

    public ConsoleObserver(MazeFrame m) {
        this();
        list.add(m);
    }

    public void addListener(MazeFrame m) {
        list.add(m);
    }
    
    @Override
    public void run() {
        var f = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while (true) {
            try {
                line = f.readLine();
            } catch (IOException e) {
                System.err.println(e);
            }
            if (line != null) {
                for (MazeFrame mazeFrame : list) {
                    if (mazeFrame.loadMaze(line) == 0) {
                        mazeFrame.findPath();
                        System.out.println("Znaleziono ścieżke");
                    } else {
                        System.err.println("Wybrany plik jest niepoprawny");
                    }
                }
            }
        }
    }
}
