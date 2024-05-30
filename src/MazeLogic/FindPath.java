/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import java.util.ArrayList;

/**
 *
 * @author golde
 */
public class FindPath {
    private Maze maze;
    
    public FindPath(Maze m) {
        maze = m;
    }
    
    public void find() {
        int start = maze.getEnd();
        int end = maze.getStart();
        ArrayList<Integer> pathList = new ArrayList<>();
        int [] parents = new int[maze.getCol() * maze.getRow()];
        boolean [] visited = new boolean[maze.getCol() * maze.getRow()];
        Queue<Integer> queue = new Queue<>();
        queue.add(start);
        visited[start] = true;
        try {
            while(!queue.isEmpty()) {
                int x = queue.get();
                if (x - maze.getCol() >= 0 && maze.getBoard(x - maze.getCol()) == ' ' && visited[x - maze.getCol()] == false) {
                    parents[x - maze.getCol()] = x;
                    queue.add(x - maze.getCol());
                    visited[x - maze.getCol()] = true;
                }
                if (x + maze.getCol() < maze.getCol() * maze.getRow() && maze.getBoard(x + maze.getCol()) == ' ' && visited[x + maze.getCol()] == false) {
                    parents[x + maze.getCol()] = x;
                    queue.add(x + maze.getCol());
                    visited[x + maze.getCol()] = true;
                }
                if (x % maze.getCol() != 0 && maze.getBoard(x - 1) == ' ' && visited[x - 1] == false) {
                    parents[x - 1] = x;
                    queue.add(x - 1);
                    visited[x - 1] = true;
                }
                if (x % maze.getCol() != maze.getCol() && maze.getBoard(x + 1) == ' ' && visited[x + 1] == false) {
                    parents[x + 1] = x;
                    queue.add(x + 1);
                    visited[x + 1] = true;
                }
            }
        }
        catch(EmptyQueueException ex) {
            System.err.println(ex);
        }
        int x = end;
        do {
            x = parents[x];
            pathList.add(x);
        } while (x != start);
        pathList.remove(pathList.size() - 1);
        maze.initPath(pathList.size());
        while(!pathList.isEmpty()) {
            maze.setPath(pathList.size() - 1, pathList.get(pathList.size() - 1));
            pathList.remove(pathList.size() - 1);
        }
        
    }
}
