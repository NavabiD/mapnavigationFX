package com.dariush.mapnav;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Navigator {

    static final String[] DEFAULT_INSTRUCTIONS = {"R2", "R3"};
    static final String NAVIGATION_INSTRUCTIONS_FILE = "NavigationInstructions.txt";

    public ArrayList<Movement> instructions = new ArrayList<>();

    public Navigator()  {
        //System.out.println("From getInstance: " + instructionsFilePath.getFileName());
    }

    public InstructionsLoadStatus loadNavigationInstructions() throws IOException, NumberFormatException {

        Path instructionsFilePath  = FileSystems.getDefault().getPath("", NAVIGATION_INSTRUCTIONS_FILE);
        File instructionsFile = new File(String.valueOf(instructionsFilePath));

        if (!instructionsFile.exists()) {
            this.loadInstructions(DEFAULT_INSTRUCTIONS);
            return InstructionsLoadStatus.INPUT_FILE_NOT_FOUND;
        }
        else {
            this.loadInstructions(instructionsFile);
            return InstructionsLoadStatus.INPUT_FILE_VALID;
        }
    }

    public InstructionsLoadStatus loadNavigationInstructions(String [] instructions) throws IOException, NumberFormatException {
            this.loadInstructions(instructions);
            return InstructionsLoadStatus.INPUT_FILE_NOT_FOUND;

    }
    public int calculateDistance () {

        MapPosition originLocation = new MapPosition();
        MapPosition currentPosition = originLocation;

        for ( Movement nextMove: this.instructions) {
            currentPosition = currentPosition.move(nextMove);
        };
        return currentPosition.distanceFromOrigin();
    }

    private void loadInstructions(String [] instructionsArray) throws MoveCommandException {
        for (String cmdStr: instructionsArray) {
            this.instructions.add(new Movement(cmdStr));
        }
    }

    private void loadInstructions(File travelMapFile) throws IOException, NumberFormatException, MoveCommandException {
        Scanner fileSacnner = null;
        try {
            fileSacnner = new Scanner(new FileReader(NAVIGATION_INSTRUCTIONS_FILE));
            String cmdStr;
            while (fileSacnner.hasNext()) {
                cmdStr = fileSacnner.next();
                this.instructions.add(new Movement(cmdStr));
            }

        } catch ( MoveCommandException e) {
            throw(e);
        }
        catch (IOException |  NumberFormatException  e) {
            throw(e);
        }
        finally {
            fileSacnner.close();
        }
    }
}
