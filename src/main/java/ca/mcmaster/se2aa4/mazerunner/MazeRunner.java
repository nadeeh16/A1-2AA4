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
    private boolean isOutOfBounds = false;

    public MazeRunner(Maze inputMaze){
        super(inputMaze);
    }

    public boolean checkBounds(){
        //check if within maze bounds
        int nextCol = playerCol;
        int nextRow = playerRow;
        if(dir == Direction.R){
            nextCol = playerCol + 1;
        }
        else if(dir == Direction.D){
            nextRow = playerRow + 1;
        }
        else if(dir == Direction.L){
            nextCol = playerCol - 1;
        }
        else{
            nextRow = playerRow - 1;
        }
        if (nextRow < 0 || nextRow >= maze.getRows() || nextCol < 0 || nextCol >= maze.getCols()){
            logger.info("Out of bounds at (" + nextRow + ", " + nextCol + "). Path invalid.");
            isOutOfBounds = true;
            return false;
        }
        return true;
    }

    @Override
    protected boolean step(char move){
        switch (move){
            case 'F':
                if(checkBounds() == false){return false;}
                if(maze.checkWall(this) == false){return false;}
                moveForward();
                return true;
            case 'R':
                changeDir('R');
                return true;
            case 'L':
                changeDir('L');
                return true;
            default:
                System.out.println("Invalid move!");
                return true;
        }
                
    }

    @Override
    protected void step(){

    }

    @Override
    protected void printResult() {
        if(onExit() && isOutOfBounds == false){
            System.out.println("Correct path");
        }
        else{
            System.out.println("Incorrect path");
        }
    }

}