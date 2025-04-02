package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Runner {
    protected int playerRow;
    protected int playerCol;
    protected int entrance;
    protected int exit;

    protected Direction dir;

    protected static final Logger logger = LogManager.getLogger();

    protected Maze maze;

    public Runner(Maze inputMaze) {
        maze = inputMaze;
        dir = Direction.R;
        entrance = maze.findEntry();
        playerRow = entrance;
        playerCol = 0;
        exit = maze.findExit();
    }

    public final void traverse(String path){
        char[] moveArray = path.toCharArray();

        for(char move : moveArray){
            if(step(move) == false){
                break;
            }
        }
        printResult();
    }

    public final void traverse(){
        while(onExit() == false){
            step();
        }
        printResult();
    }

    protected abstract boolean step(char move);
    protected abstract void step();
    protected abstract void printResult();

    public void changeDir(char turnDir){
        if(turnDir == 'L'){
            dir = dir.turnLeft();
        }
        else if(turnDir == 'R'){
            dir = dir.turnRight();
        }
        else{
            logger.info("Invalid direction. Must be L or R.");
        }
    }

    public void moveForward(){
        if(dir == Direction.U){
            playerRow--;
        }
        else if(dir == Direction.R){
            playerCol++;
        }
        else if(dir == Direction.D){
            playerRow++;
        }
        else if(dir == Direction.L){
            playerCol--;
        }
    }

    public Boolean onExit(){
        if(playerCol == maze.getCols() - 1 && playerRow == exit){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRow(){
        return playerRow;
    }
    public int getCol(){
        return playerCol;
    }
    public Direction getDir(){
        return dir;
    }

    
}
