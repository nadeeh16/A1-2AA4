package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeRunnerTest {
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

        MazeRunner mazeRunner = new MazeRunner(maze);
        mazeRunner.checkPath("FFFF");
        assertEquals("Correct path", out.toString().trim());
    }

    @Test
    public void testWrongPath() {
        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);

        MazeRunner mazeRunner = new MazeRunner(maze);
        mazeRunner.checkPath("FFFFF");
        assertEquals("Incorrect path", out.toString().trim());
    }

    @Test
    public void testExtraInputButRight(){
        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);

        MazeRunner mazeRunner = new MazeRunner(maze);
        mazeRunner.checkPath("FFFFRRFRRF");
        assertEquals("Correct path", out.toString().trim());
    }

    @Test
    public void testChangeDirectionButRight(){
        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);

        MazeRunner mazeRunner = new MazeRunner(maze);
        mazeRunner.checkPath("FFFFR");
        assertEquals("Correct path", out.toString().trim());
    }

    @Test
    public void testReachEndButGoBack(){
        String mazePath = "./examples/straight.maz.txt";
        Maze maze = new Maze(mazePath);

        MazeRunner mazeRunner = new MazeRunner(maze);
        mazeRunner.checkPath("FFFFRRF");
        assertEquals("Incorrect path", out.toString().trim());
    }
}
