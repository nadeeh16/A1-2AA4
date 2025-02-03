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
                    break;
                case 'D':
                    dir = 'L';
                    break;
                case 'L':
                    dir = 'U';
                    break;
                case 'U':
                    dir = 'R';
                    break;
            }
        }
        else if(turnDir == 'L'){
            switch(currDir){
                case 'R':
                    dir = 'U';
                    break;
                case 'D':
                    dir = 'R';
                    break;
                case 'L':
                    dir = 'D';
                    break;
                case 'U':
                    dir = 'L';
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
                        if(maze.checkWall(this)){
                            playerCol++; // Move forward
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
                        if(maze.checkWall(this)){
                            playerRow++; // Move forward
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
                        if(maze.checkWall(this)){
                            playerCol--; // Move forward
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
                        if(maze.checkWall(this)){
                            playerRow--; // Move forward
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

    public void moveForward(){
        if(dir == 'U'){
            playerRow--;
        }
        else if(dir == 'R'){
            playerCol++;
        }
        else if(dir == 'D'){
            playerRow++;
        }
        else if(dir == 'L'){
            playerCol--;
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

    public int getRow(){
        return playerRow;
    }
    public int getCol(){
        return playerCol;
    }
    public char getDir(){
        return dir;
    }

    public Boolean onExit(){
        if(playerCol == maze.getCols() - 1 && playerRow == exit){
            return true;
        }
        else{
            return false;
        }
    }

}