/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeGUI;

/**
 *
 * @author golde
 */
public class FileNotCorrectException extends Exception {
    final private String error;
    public FileNotCorrectException(String s) {
        super();
        error = s;
    }
    
    @Override
    public String toString() {
        return error;
    }
}
