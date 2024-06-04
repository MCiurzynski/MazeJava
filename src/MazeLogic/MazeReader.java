/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author golde
 */
public class MazeReader {

    String fileName;

    public MazeReader(String name) {
        fileName = name;
    }

    public void read(Maze m) throws FileNotCorrectException {
        String line;
        int col;
        int row = 1;
        int n;
        try {
            isFileCorrect();
        } catch (FileNotCorrectException ex) {
            throw ex;
        }
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            line = file.readLine();
            col = line.length();
            while ((line = file.readLine()) != null) {
                row++;
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            m = null;
            return;
        } catch (IOException ex) {
            System.err.println("Błąd podczas odczytu pliku " + ex);
            m = null;
            return;
        }
        m.setRow(row);
        m.setCol(col);
        m.makeBoard();
        n = 0;
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            while ((line = file.readLine()) != null) {
                for (char i : line.toCharArray()) {
                    if (i == 'P') {
                        m.setStart(n);
                        m.setBoard(n++, ' ');
                    } else if (i == 'K') {
                        m.setEnd(n);
                        m.setBoard(n++, ' ');
                    } else {
                        m.setBoard(n++, i);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Błąd podczas odczytu pliku " + ex);
            m = null;
        }
    }

    protected void isFileCorrect() throws FileNotCorrectException {
        String nextLine, line;
        int start = 0, end = 0, lineNumber = 0, col;
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotCorrectException("Wybrany plik nie istnieje");
        }
        try (var f = new BufferedReader(new FileReader(file))) {
            line = f.readLine();
            if (line == null) {
                throw new FileNotCorrectException("Plik jest pusty");
            }
            col = line.length();
            for (char i : line.toCharArray()) {
                switch (i) {
                    case 'X':
                        continue;
                    case 'P':
                        start++;
                        continue;
                    case 'K':
                        end++;
                        continue;
                    default:
                        throw new FileNotCorrectException("Nieprawidłowy znak na górze labiryntu");
                }
            }
            line = f.readLine();
            if (line == null) {
                throw new FileNotCorrectException("Za mała ilość wierszy");
            }
            while ((nextLine = f.readLine()) != null) {
                if (line.length() != col) {
                    throw new FileNotCorrectException("Szerokość wierszy nie jest stała");
                }
                if (lineNumber % 2 == 0) {
                    switch (line.charAt(0)) {
                        case 'X':
                            break;
                        case 'P':
                            start++;
                            break;
                        case 'K':
                            end++;
                            break;
                        default:
                            throw new FileNotCorrectException("Nieprawidłowy znak na lewym boku labiryntu");
                    }
                    switch (line.charAt(line.length() - 1)) {
                        case 'X':
                            break;
                        case 'P':
                            start++;
                            break;
                        case 'K':
                            end++;
                            break;
                        default:
                            throw new FileNotCorrectException("Nieprawidłowy znak na prawym boku labiryntu");
                    }
                    for (int i = 1; i < nextLine.length() - 1; i++) {
                        char c = line.charAt(i);
                        if (c != 'X' && c != ' ') {
                            throw new FileNotCorrectException("Nieprawidłowy znak w środku labiryntu");
                        }
                        if (c != ' ' && i % 2 == 1) {
                            throw new FileNotCorrectException("Nieprawidłowy znak w środku labiryntu (oczekiwany ' ')");
                        }
                    }
                } else {
                    if (line.charAt(0) != 'X' || line.charAt(line.length() - 1) != 'X') {
                        throw new FileNotCorrectException("Nieprawidłowy znak na boku labiryntu (oczekiwany 'X'");
                    }
                    for (int i = 1; i < nextLine.length() - 1; i++) {
                        char c = line.charAt(i);
                        if (c != 'X' && c != ' ') {
                            throw new FileNotCorrectException("Nieprawidłowy znak w środku labiryntu");
                        }
                        if (c != 'X' && i % 2 == 0) {
                            throw new FileNotCorrectException("Nieprawidłowy znak w środku labiryntu (oczekiwany 'X')");
                        }
                    }
                }
                lineNumber++;
                line = nextLine;
            }
            for (char i : line.toCharArray()) {
                switch (i) {
                    case 'X':
                        continue;
                    case 'P':
                        start++;
                        continue;
                    case 'K':
                        end++;
                        continue;
                    default:
                        throw new FileNotCorrectException("Nieprawidłowy znak na dole labiryntu");
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        if (start != 1) {
            throw new FileNotCorrectException("Nieprawidłowa ilość wejść");
        }
        if (end != 1) {
            throw new FileNotCorrectException("Nieprawidłowa ilość wyjść");
        }
    }
}
