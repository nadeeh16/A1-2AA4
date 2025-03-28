package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;
import java.io.*;
import java.lang.*;

class MazeRunner extends Runner {
    public MazeRunner(Maze inputMaze){
        super(inputMaze);
    }

    public boolean checkBounds(int nextRow, int nextCol){
        //check if within maze bounds
        if (nextRow < 0 || nextRow >= maze.getRows() || nextCol < 0 || nextCol >= maze.getCols()){
            logger.info("Out of bounds at (" + nextRow + ", " + nextCol + "). Path invalid.");
            return false;
        }
        return true;
    }

    public boolean move(String path) {
        char[] movementArray = path.toCharArray();

        for (char move : movementArray) {
            if (dir == Direction.R) {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow, playerCol+1) == false){
                            playerCol++;
                            return false;
                        }
                        if(maze.checkWall(this)){
                            playerCol++; // Move forward
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('R'); // Turn right
                        break;
                    case 'L':
                        changeDir('L'); // Turn left
                        break;
                }
            } else if (dir == Direction.D) {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow + 1, playerCol) == false){
                            playerRow++;
                            return false;
                        }
                        if(maze.checkWall(this)){
                            playerRow++; // Move forward
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('R'); // Turn right
                        break;
                    case 'L':
                        changeDir('L'); // Turn left
                        break;
                }
            } else if (dir == Direction.L) {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow, playerCol - 1) == false){
                            playerCol--;
                            return false;
                        }
                        if(maze.checkWall(this)){
                            playerCol--; // Move forward
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('R'); // Turn right
                        break;
                    case 'L':
                        changeDir('L'); // Turn left
                        break;
                }
            } else if (dir == Direction.U) {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow - 1, playerCol) == false){
                            playerRow--;
                            return false;
                        }
                        if(maze.checkWall(this)){
                            playerRow--; // Move forward
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('R'); // Turn right
                        break;
                    case 'L':
                        changeDir('L'); // Turn left
                        break;
                }
            }
        }
        if(playerRow == exit && playerCol == maze.getCols() - 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void checkPath(String path){
        if(move(path)){
            System.out.println("Correct path");
        }
        else{
            int exitcol = maze.getCols() - 1;
            //logger.info("Runner ended at: (" + playerRow + ", " + playerCol + ") while exit is at: (" + exit + ", " + exitcol + ")");
            System.out.println("Incorrect path");
        }
    }

}