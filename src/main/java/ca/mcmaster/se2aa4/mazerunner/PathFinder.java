package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder extends Runner {
    private StringBuilder path;
    private StringBuilder factorizedPath;
    private static final Logger logger = LogManager.getLogger();

    private String factorizedPaths = "";

    public PathFinder(Maze inputMaze){
        super(inputMaze);
        path = new StringBuilder();
        factorizedPath = new StringBuilder();
    }

    public void factorizePath(){
        int count = 1;

        for(int i = 1; i <= path.length(); i++){
            if(i == path.length() || path.charAt(i) != path.charAt(i - 1)){
                if(count > 1){
                    factorizedPath.append(count);
                }
                factorizedPath.append(path.charAt(i - 1));
                count = 1;
            }
            else{
                count++;
            }
        }
        factorizedPaths = factorizedPath.toString();
        System.out.println(factorizedPath.toString());
    }

    public String getFactorizedPaths() {
        return factorizedPaths;
    }

    @Override
    protected boolean step(char move){
        return false;
    }

    @Override
    protected void step(){
        if(maze.checkRight(this) == false && maze.checkWall(this) == false){
            //meaning there is a wall on both the right and ahead
            changeDir('L');
            path.append('L');
        }
        else if(maze.checkRight(this) == false && maze.checkWall(this)){
            //theres something on the right and not ahead
            moveForward();
            path.append('F');
        }
        else if(maze.checkRight(this)){
            if(path.charAt(path.length() - 1) == 'R' && maze.checkWall(this)){
                moveForward();
                path.append('F');
            }else{
                changeDir('R');
                path.append('R');
            }
            //theres something on the right and not ahead
        }
    }

    @Override
    protected void printResult(){
        factorizePath();
    }
}
