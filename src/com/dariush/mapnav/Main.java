package com.dariush.mapnav;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author Peggy Fisher
 */
public class Main extends Application {

    private static final String APPLICATION_TITLE = "Map Navigation Program";
    private static final String SCREEN_DESCRIPTION = "Distance of drop location to the Headquarters";
    private static final String RESULT_DISPLAY_LABEL = "Calculated Distance:";
    private static final String INSTRUCTIONS_FILE_STATUS_LABEL = "Navigation Instructions Data:";
    private static final String INSTRUCTIONS_FILE_STATUS_NOT_AVAILABLE = "Input file not found. Using default instruction set: [R2, R3].";
    private static final String INSTRUCTION_MOVE_COMMAND_INVALID = "Invalid navigation move command.";
    private static final String INSTRUCTIONS_FILE_STATUS_VALID = "Input file successfully loaded.";
    private static final String INSTRUCTIONS_FILE_STATUS_ERROR = "Invalid navigation move steps. ";
    private static final String NAVIGATION_RESULT_LABEL = "Map Navigation Result";
    private static final String EMPTY_STRING = "";


    @Override
    public void start(Stage primaryStage) {

        InstructionsLoadStatus instructionsLoadStatus;

        //Create the grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        Scene scene = new Scene(grid, 650, 110);

        //Add screen description
        Text sceneTitle = new Text(SCREEN_DESCRIPTION);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));grid.add(sceneTitle, 0, 0, 2, 1);


        //Create the navigator and load the navigation instructions
        Navigator navigator = new Navigator();
        try {
            instructionsLoadStatus = navigator.loadNavigationInstructions();
        }
        catch (MoveCommandException  e) {
            instructionsLoadStatus = InstructionsLoadStatus.MOVE_DIRECTION_INVALID;
        }
        catch (IOException | NumberFormatException  e) {
            instructionsLoadStatus = InstructionsLoadStatus.MOVE_STEPS_INVALID;
        }

        //Display instructions file load status
        Label instructionFileStatLabel = new Label(INSTRUCTIONS_FILE_STATUS_LABEL);
        grid.add(instructionFileStatLabel,0, 2 );
        Label instructionFileConfirmed = new Label(INSTRUCTIONS_FILE_STATUS_VALID);
        Label instructionFileNotAvailable = new Label(INSTRUCTIONS_FILE_STATUS_NOT_AVAILABLE);
        Label instructionMoveCommandInvalid = new Label(INSTRUCTION_MOVE_COMMAND_INVALID);
        Label instructionFileError = new Label(INSTRUCTIONS_FILE_STATUS_ERROR);
        switch (instructionsLoadStatus) {
            case INPUT_FILE_NOT_FOUND:
                grid.add(instructionFileNotAvailable,1, 2);
                break;
            case MOVE_STEPS_INVALID:
                grid.add(instructionFileError, 1, 2);
                break;
            case MOVE_DIRECTION_INVALID:
                grid.add(instructionMoveCommandInvalid, 1, 2);
                break;
            default: //INPUT_FILE_VALID:
                grid.add(instructionFileConfirmed,1, 2 );
                break;
        }

        //Calculated and display distance
        if (instructionsLoadStatus == InstructionsLoadStatus.INPUT_FILE_VALID ||
                instructionsLoadStatus == InstructionsLoadStatus.INPUT_FILE_NOT_FOUND) {
            Label resultDisplayLabel = new Label(RESULT_DISPLAY_LABEL);
            grid.add(resultDisplayLabel,0, 3 );
            Label resultDisplay = new Label (EMPTY_STRING);
            grid.add(resultDisplay, 1,3);
            Integer distance = navigator.calculateDistance();
            resultDisplay.setText(Integer.toString(distance));
        }

        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
