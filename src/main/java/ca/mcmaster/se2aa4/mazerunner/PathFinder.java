package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {
    private MazeRunner pathFinder;
    private Maze maze;
    private StringBuilder path;
    private StringBuilder factorizedPath;
    private static final Logger logger = LogManager.getLogger();

    private String factorizedPaths = "";

    public PathFinder(String mazePath, Maze inputMaze){
        maze = new Maze(mazePath);
        pathFinder = new MazeRunner(maze);
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

    public void findPath(){
        //move forward until you encounter a wall...
        while(pathFinder.onExit() == false){
            if(maze.checkRight(pathFinder) == false && maze.checkWall(pathFinder) == false){
                //meaning there is a wall on both the right and ahead
                pathFinder.changeDir('L');
                path.append('L');
            }
            else if(maze.checkRight(pathFinder) == false && maze.checkWall(pathFinder)){
                //theres something on the right and not ahead
                pathFinder.moveForward();
                path.append('F');
            }
            else if(maze.checkRight(pathFinder)){
                if(path.charAt(path.length() - 1) == 'R' && maze.checkWall(pathFinder)){
                    pathFinder.moveForward();
                    path.append('F');
                }else{
                    pathFinder.changeDir('R');
                    path.append('R');
                }
                //theres something on the right and not ahead
            }
        }

        //logger.info(path.toString());
        factorizePath();
    }
}
