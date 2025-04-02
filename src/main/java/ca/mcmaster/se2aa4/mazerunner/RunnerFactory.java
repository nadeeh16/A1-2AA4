package ca.mcmaster.se2aa4.mazerunner;

public class RunnerFactory {
    public static Runner createRunner(String traversalPath, Maze maze){
        if(traversalPath == null){
            return new PathFinder(maze);
        }
        else{
            return new MazeRunner(maze);
        }
    }
}
