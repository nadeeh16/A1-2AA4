package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PathFinderTest {
    private static final java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    
    @BeforeAll
    public static void setUp() {
        System.setOut(new java.io.PrintStream(out));
    }

    @BeforeEach
    public void reset() {
        out.reset();
    }

    @Test
    public void testStraightMazePathFinder() {

        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);

        PathFinder pathFinder = new PathFinder(mazePath, maze);
        pathFinder.findPath();
        assertEquals("4F", out.toString().trim());
    }

    @Test
    public void testDirectMazePathFinder() {
        String mazePath = "./examples/direct.maz.txt";
        Maze maze = new Maze(mazePath);

        PathFinder pathFinder = new PathFinder(mazePath, maze);
        pathFinder.findPath();
        assertEquals("FR2FL3FRFLFRFL2F", out.toString().trim());
    }
}
