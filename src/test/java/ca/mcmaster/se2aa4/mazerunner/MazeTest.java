package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    @Test
    public void TestFindEntryExitStraightMaze(){
        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);
        int entry = maze.findEntry();
        int exit = maze.findExit();
        
        assertAll(
            () -> assertEquals(2, entry, "Entry should be at 2"),
            () -> assertEquals(2, exit, "Exit should be at 2")
        );
    }

    @Test
    public void TestFindEntryExitRectangleMaze(){
        String mazePath = "./examples/rectangle.maz.txt";
        Maze maze = new Maze(mazePath);
        int entry = maze.findEntry();
        int exit = maze.findExit();
        
        assertAll(
            () -> assertEquals(12, entry, "Entry should be at 12"),
            () -> assertEquals(10, exit, "Exit should be at 10")
        );
    }
}
