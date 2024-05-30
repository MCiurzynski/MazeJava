/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import javax.swing.JOptionPane;

/**
 *
 * @author golde
 */
public class Logger {

    Queue<String> queue;

    public Logger() {
        queue = new Queue<>();
    }

    public synchronized void addLog(String s) {
        queue.add(s);
        notify();
    }

    public synchronized String getLog() {
        try {
            while (queue.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            return queue.get();
        } catch (EmptyQueueException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
