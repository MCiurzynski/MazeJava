/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author golde
 */
public class BinaryMazeReader extends MazeReader {

    private int fileId;
    private byte escape;
    private short columns;
    private short rows;
    private short startX;
    private short startY;
    private short endX;
    private short endY;
    private int counter;
    private int solutionOffset;
    private byte separator;
    private byte wall;
    private byte path;

    private byte tmp;
    private int value;
    private int count;
    public BinaryMazeReader(String name) {
        super(name);
    }

    @Override
    public void read(Maze m) {
        try (var fileByte = new FileInputStream(fileName)) {
            var file = new DataInputStream(fileByte);
            fileId = Integer.reverseBytes(file.readInt());
            escape = file.readByte();
            columns = Short.reverseBytes(file.readShort());
            rows = Short.reverseBytes(file.readShort());
            startX = Short.reverseBytes(file.readShort());
            startY = Short.reverseBytes(file.readShort());
            endX = Short.reverseBytes(file.readShort());
            endY = Short.reverseBytes(file.readShort());
            file.readInt();
            file.readInt();
            file.readInt();
            counter = Integer.reverseBytes(file.readInt());
            solutionOffset = Integer.reverseBytes(file.readInt());
            separator = file.readByte();
            wall = file.readByte();
            path = file.readByte();
            
            startX--;
            startY--;
            endX--;
            endY--;
            m.setCol(columns);
            m.setRow(rows);
            m.setStart(startX + startY * columns);
            m.setEnd(endX + endY * columns);
            
            m.makeBoard();
            int n = 0;
            byte [] bytes = new byte[counter * 3];
            fileByte.read(bytes);
            for (int i = 0; i < counter * 3; i+=3) {
                value = bytes[i + 1];
                if (value < 0)
                    value += 256;
                count = bytes[i + 2];
                if (count < 0)
                    count += 256;
                for (int j = 0; j <= count; j++) {
                    if (value == path)
                        m.setBoard(n++, ' ');
                    else if (value == wall)
                        m.setBoard(n++, 'X');
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono pliku");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Błąd odczytywania plików");
        }
        
    }

    @Override
    protected void isFileCorrect() throws FileNotCorrectException {

    }
}
