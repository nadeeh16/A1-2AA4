package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//tests for main method
public class MainTest {
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
    public void testMainWithArgs() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-p", "FFFFRRFRRF"};
        Main.main(args);
        assertEquals("Correct path", out.toString().trim());
    }

    @Test
    public void testMainWithArg() {
        String[] args = {"-i", "./examples/straight.maz.txt"};
        Main.main(args);
        assertEquals("4F", out.toString().trim());
    }

    @Test
    public void IncorrectPath() {
        String[] args = {"-i", "./examples/direct.maz.txt", "-p", "FFFFRRFRRF"};
        Main.main(args);
        assertEquals("Incorrect path", out.toString().trim());
    }

    @Test
    public void TurnRightTest() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-p", "FFFRFF"};
        Main.main(args);
        assertEquals("Incorrect path", out.toString().trim());
    }

    @Test
    public void TurnLeftTest() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-p", "FFFLFF"};
        Main.main(args);
        assertEquals("Incorrect path", out.toString().trim());
    }

    @Test
    public void TestPathFinderOnDirectMaze() {
        String[] args = {"-i", "./examples/direct.maz.txt"};
        Main.main(args);
        assertEquals("FR2FL3FRFLFRFL2F", out.toString().trim());
    }

    @Test
    public void TestPathFinderOnRectangularMaze() {
        String[] args = {"-i", "./examples/rectangle.maz.txt"};
        Main.main(args);
        assertEquals("FR7FL4F2L4FR2FR2F2L2FR16FR2FR14F2L4FR2FR6F2L4FR2FR6F2L2FR2FR2FL2F2L2FR2FL2FR4FR2FR2F2L2FR2FR4F2L4FR2FR6F2L6FR2FR6F2L4FR2FR4F2L2FR2FR2FL2F2L2FR2FL2FR2FL2FR2FR4FR2FL2FR2F2L2FR2FR2F2L2FL4FR2FL8FR2F2L2FR6FR2FR2F2L4FR6FR2FR4F2L2FR2FR2F2L2FR2FR4F2L4FL4FR2FR6FR4F2L4FR2FR6FL2F2L2FR2FR4FR2F2L2FR2FR2F2L2FR2FR4F2L4FR2FR4F2L4FR2FR10FR2F2L6F2L4FR8FR2FR6F2L4FR2FR4F2L2FR2FR4FL6F2L6FR2FR4F2L4FR2FR4F2L6FR2FR4F2L6FR2FR2F2L4FR2FR2FL2FR2FR2FL2FR2F2L2FL2FR2FL2FL2FR2FR2F2L2FR6FR4FR2FR2FL2F2L2FR2FL2FL2FR2F2L2FR2FR6FR4FR2FR2F2L2FL4FR2FR4FL2FR2FR4F2L2FR4F2L4FR2FR6FR4F2L4FR2FR4FL6FR2FL2FR4F2L4FR2FR6F2L2FR2FR6F2L2FR2FR4F2L2FR2FR2F2L2FL2FR2FL2FR4FR2FR2F2L2FR2FR4F2L4FR2FR6F2L6FR2FR8FR2F2L2FL6FR2FR6FL2F2L2FR2FR2F2L2FR2FR2F2L2FR2FL2FR2FR2F2L2FR2FR4FR4F2L3FRF", out.toString().trim());
    }

}
