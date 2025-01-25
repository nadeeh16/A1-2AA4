package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;
import java.io.*;
import java.lang.*;

class MazeRunner{
    private int playerRow;
    private int playerCol;
    private char dir;
    private int entrance;
    private int exit;

    private static final Logger logger = LogManager.getLogger();

    Maze maze;

    public MazeRunner(Maze inputMaze){
        maze = inputMaze;
        dir = 'R';
        entrance = maze.findEntry();
        playerRow = entrance;
        playerCol = 0;
        exit = maze.findExit();
    }

    public void changeDir(char currDir, char turnDir){
        if(turnDir == 'R'){
            switch(currDir){
                case 'R':
                    dir = 'D';
                    logger.info("Turned right, now facing down");
                    break;
                case 'D':
                    dir = 'L';
                    logger.info("Turned right, now facing left");
                    break;
                case 'L':
                    dir = 'U';
                    logger.info("Turned right, now facing up");
                    break;
                case 'U':
                    dir = 'R';
                    logger.info("Turned right, now facing right");
                    break;
            }
        }
        else if(turnDir == 'L'){
            switch(currDir){
                case 'R':
                    dir = 'U';
                    logger.info("Turned left, now facing up");
                    break;
                case 'D':
                    dir = 'R';
                    logger.info("Turned left, now facing right");
                    break;
                case 'L':
                    dir = 'D';
                    logger.info("Turned left, now facing down");
                    break;
                case 'U':
                    dir = 'L';
                    logger.info("Turned left, now facing left");
                    break;
            }
        }
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
            if (dir == 'R') {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow, playerCol+1) == false){
                            playerCol++;
                            return false;
                        }
                        if(maze.checkWall(playerRow, playerCol+1)){
                            playerCol++; // Move forward
                            logger.info("Facing right, moved forward");
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('R', 'R'); // Turn right
                        break;
                    case 'L':
                        changeDir('R', 'L'); // Turn left
                        break;
                }
            } else if (dir == 'D') {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow + 1, playerCol) == false){
                            playerRow++;
                            return false;
                        }
                        if(maze.checkWall(playerRow + 1, playerCol)){
                            playerRow++; // Move forward
                            logger.info("Facing down, moved forward");
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('D', 'R'); // Turn right
                        break;
                    case 'L':
                        changeDir('D', 'L'); // Turn left
                        break;
                }
            } else if (dir == 'L') {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow, playerCol - 1) == false){
                            playerCol--;
                            return false;
                        }
                        if(maze.checkWall(playerRow, playerCol - 1)){
                            playerCol--; // Move forward
                            logger.info("Facing left, moved forward");
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('L', 'R'); // Turn right
                        break;
                    case 'L':
                        changeDir('L', 'L'); // Turn left
                        break;
                }
            } else if (dir == 'U') {
                switch (move) {
                    case 'F':
                        if(checkBounds(playerRow - 1, playerCol) == false){
                            playerRow--;
                            return false;
                        }
                        if(maze.checkWall(playerRow - 1, playerCol)){
                            playerRow--; // Move forward
                            logger.info("Facing up, moved forward");
                            break;
                        }
                        return false;
                    case 'R':
                        changeDir('U', 'R'); // Turn right
                        break;
                    case 'L':
                        changeDir('U', 'L'); // Turn left
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
            logger.info("It was the correct path");
        }
        else{
            int exitcol = maze.getCols() - 1;
            logger.info("Runner ended at: (" + playerRow + ", " + playerCol + ") while exit is at: (" + exit + ", " + exitcol + ")");
            logger.info("It was the wrong path");
        }
    }

}