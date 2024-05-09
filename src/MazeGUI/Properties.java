/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeGUI;

import java.awt.Color;

/**
 *
 * @author golde
 */
public class Properties {
    private static int cellSize = 10;
    private static Color roomColor = Color.WHITE;
    private static Color wallColor = Color.BLACK;
    private static Color pathColor = Color.GREEN;
    private static Color startColor = Color.RED;
    private static Color endColor = Color.RED;
    
    public synchronized static void setCellSize(int x) {
        cellSize = x;
    }
    
    public synchronized static int getCellSize() {
        return cellSize;
    }
    
    public synchronized static void setRoomColor(Color c) {
        roomColor = c;
    }
    
    public synchronized static Color getRoomColor() {
        return roomColor;
    }
    
    public synchronized static void setWallColor(Color c) {
        wallColor = c;
    }
    
    public synchronized static Color getWallColor() {
        return wallColor;
    }
    
    public synchronized static void setPathColor(Color c) {
        pathColor = c;
    }
    
    public synchronized static Color getPathColor() {
        return pathColor;
    }
    
    public synchronized static void setStartColor(Color c) {
        startColor = c;
    }
    
    public synchronized static Color getStartColor() {
        return startColor;
    }
    
    public synchronized static void setEndColor(Color c) {
        endColor = c;
    }
    
    public synchronized static Color getEndColor() {
        return endColor;
    }
}
