package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;
import java.io.*;

class Maze{
    private char mazeGrid[][];
    private int rows;
    private int cols;
    private static final Logger logger = LogManager.getLogger();

    public Maze(String mazePath){
        findDimensions(mazePath);
        loadMaze(mazePath);
    }

    public int findEntry(){
        for(int row = 0; row < rows; row++){
            if(mazeGrid[row][0] != '#'){
                //logger.info("Entrance: (" + row + ", 0)");
                return row;
            }
        }
        return 0;
    }

    public int findExit(){
        int finalCol = cols - 1;
        for(int row = 0; row < rows; row++){
            if(mazeGrid[row][finalCol] != '#'){
                //logger.info("Exit: (" + row + ", "+ finalCol + ")");
                return row;
            }
        }
        return 0;
    }

    public boolean checkWall(Runner thisRunner){
        int runnerRow = thisRunner.getRow();
        int runnerCol = thisRunner.getCol();
        if (thisRunner.getDir() == Direction.U){
            if (mazeGrid[runnerRow-1][runnerCol] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.D){
            if (mazeGrid[runnerRow+1][runnerCol] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.R){
            if (mazeGrid[runnerRow][runnerCol+1] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.L){
            if (mazeGrid[runnerRow][runnerCol-1] == ' '){
                return true;
            }
        }
        return false;
    }

    public boolean checkRight(Runner thisRunner){
        int runnerRow = thisRunner.getRow();
        int runnerCol = thisRunner.getCol();
        if (thisRunner.getDir() == Direction.U){
            if (mazeGrid[runnerRow][runnerCol+1] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.D){
            if (mazeGrid[runnerRow][runnerCol-1] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.R){
            if (mazeGrid[runnerRow+1][runnerCol] == ' '){
                return true;
            }
        }
        else if (thisRunner.getDir() == Direction.L){
            if (mazeGrid[runnerRow-1][runnerCol] == ' '){
                return true;
            }
        }
        return false;
    }

    public int getCols(){
        return cols;
    }

    public int getRows(){
        return rows;
    }

    public void findDimensions(String mazePath){
        rows = 0;
        cols = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(mazePath));

            String line;
            while((line = reader.readLine()) != null){
                rows++;
                cols = line.length();
            }
        }catch(Exception e){
            System.out.println("Error");
        }

        mazeGrid = new char[rows][cols];

    }

    public void loadMaze(String mazePath){
        try{
            //int entrance = findEntry();
            BufferedReader reader = new BufferedReader(new FileReader(mazePath));

            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    mazeGrid[rowIndex][idx] = line.charAt(idx);
                }
                rowIndex++;
            }
            
        }catch(Exception e){
            System.out.print("");
        }
    }

    // public void printMaze(){
    //     for(int row = 0; row < rows; row++){
    //         for(int col = 0; col < cols; col++){
    //             System.out.print(mazeGrid[row][col]);
    //         }
    //         System.out.println();
    //     }
    // }
}