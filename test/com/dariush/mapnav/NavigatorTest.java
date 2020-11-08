package com.dariush.mapnav;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {

    @Test
    void calculateDistance() {
        Navigator navigator = new Navigator();
        try {
            navigator.loadNavigationInstructions();
            int result = navigator.calculateDistance();
            assertEquals(result, 209);
        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void loadInstructionsValid() {

        String[] instructions = new String[] {"R5", "L5", "R5", "R3"};
        Navigator navigator = new Navigator();

        try {
            navigator.loadNavigationInstructions(instructions);
            int result = navigator.calculateDistance();
            assertEquals(result, 12);
        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void loadInstructionsInvalidCmd() {

        String[] instructions = new String[] {"R5", "K5", "R5", "R3"};
        Navigator navigator = new Navigator();

        try {
            navigator.loadNavigationInstructions(instructions);
            int result = navigator.calculateDistance();
            assertEquals(result, 12);

        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
   }

    @Test
    void loadInstructionsInvalidStepCount() {
        String[] instructions = new String[] {"R5", "L5k", "R5", "R3"};
        Navigator navigator = new Navigator();

        try {
            navigator.loadNavigationInstructions(instructions);
            int result = navigator.calculateDistance();
            assertEquals(result, 12);

        } catch (IOException  | NumberFormatException ex) {
            assertTrue(ex instanceof NumberFormatException);
        }
    }
}