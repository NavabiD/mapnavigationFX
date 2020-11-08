package com.dariush.mapnav;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void getRelativeDirectionRight() {
        try {
            Movement aMove = new Movement("R3");
            RelativeDirection result = aMove.getRelativeDirection();
            assertEquals(result, RelativeDirection.RIGHT);
        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void getRelativeDirectionLeft() {
        try {
            Movement aMove = new Movement("L3");
            RelativeDirection result = aMove.getRelativeDirection();
            assertEquals(result, RelativeDirection.LEFT);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void getRelativeDirectionInvalid() {
        try {
            Movement aMove = new Movement("P3");
            RelativeDirection result = aMove.getRelativeDirection();
            assertEquals(result, RelativeDirection.LEFT);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void getStepsR() {
        try {
            Movement aMove = new Movement("R3");
            int result = aMove.getSteps();
            assertEquals(result, 3);
        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void getStepsL() {
        try {
            Movement aMove = new Movement("L3");
            int result = aMove.getSteps();
            assertEquals(result, 3);
        } catch (IOException ex) {
            assertTrue(ex instanceof MoveCommandException);
        }
    }
}