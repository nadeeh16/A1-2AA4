package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;
import java.io.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
    //add the option
        Options options = new Options();
        options.addOption("i", "input", true, "maze file");
        options.addOption("p", "path", true, "Path you wanna take");

        //create a parser 
        CommandLineParser parser = new DefaultParser();

        logger.info("** Starting Maze Runner");
        try {

            CommandLine cmd = parser.parse(options, args);

            String mazePath = cmd.getOptionValue("i");
            

            logger.info("**** Reading the maze from file " + mazePath);
            BufferedReader reader = new BufferedReader(new FileReader(mazePath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }

            Maze maze = new Maze(mazePath);
            maze.printMaze();

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        // logger.info("PATH NOT COMPUTED");
        // logger.info("**** Computing path");
        logger.info("** End of MazeRunner");
    }
}  