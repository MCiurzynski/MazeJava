/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

/**
 *
 * @author golde
 */
public class EmptyQueueException extends Exception {

    public EmptyQueueException() {
        super();
    }
    @Override
    public String toString() {
        return "Queue is empty";
    }
}
