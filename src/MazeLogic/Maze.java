/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

/**
 *
 * @author golde
 */
public class Maze {

    private int row;
    private int col;
    private int start;
    private int end;
    private char[] board;

    public void setRow(int r) {
        row = r;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int c) {
        col = c;
    }

    public int getCol() {
        return col;
    }

    public void setStart(int x) {
        start = x;
    }

    public int getStart() {
        return start;
    }

    public void setEnd(int x) {
        end = x;
    }

    public int getEnd() {
        return end;
    }

    public void setBoard(int i, char c) {
        board[i] = c;
    }

    public char getBoard(int i) {
        return board[i];
    }

    public void makeBoard() {
        board = new char[row * col];
    }

}
