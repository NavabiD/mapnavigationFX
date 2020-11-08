package com.dariush.mapnav;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MapPositionTest {

    @Test
    void distanceFromOriginNoMove() {
        MapPosition mapPosition = new MapPosition();
        int result = mapPosition.distanceFromOrigin();
        assertEquals(result, 0);
    }

    @Test
    void distanceFromOrigin1RMove() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("R5");
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 5);
        }
        catch (IOException ex){
            assertTrue(ex instanceof IOException);
        }
    }

    @Test
    void distanceFromOrigin1RMoveIOExc() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("P5");
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 5);
        }
        catch (IOException ex){
            assertTrue(ex instanceof IOException);
        }
    }

    @Test
    void distanceFromOrigin1RMoveMoveCmdExc() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("P5");
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 5);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void distanceFromOrigin2RMoves() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("R5");
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 10);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }


    @Test
    void distanceFromOrigin3RMoves() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("R5");
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 5);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void distanceFromOriginLoopMove() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("R5");
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 0);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void distanceFromOrigin2LMoves() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("L5");
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 10);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }

    @Test
    void distanceFromOrigin6Moves() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement lMove3 = new Movement("L3");
            Movement rMove2 = new Movement("R2");
            Movement lMove5 = new Movement("L5");
            Movement rMove1 = new Movement("R1");
            Movement lMove1 = new Movement("L1");
            Movement lMove2 = new Movement("L2");
            mapPosition.move(lMove3);
            mapPosition.move(rMove2);
            mapPosition.move(lMove5);
            mapPosition.move(rMove1);
            mapPosition.move(lMove1);
            mapPosition.move(lMove2);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 10);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }
    @Test
    void distanceFromOriginException() {
        MapPosition mapPosition = new MapPosition();
        try {
            Movement aMove = new Movement("P5");
            mapPosition.move(aMove);
            mapPosition.move(aMove);
            int result = mapPosition.distanceFromOrigin();
            assertEquals(result, 10);
        }
        catch (IOException ex){
            assertTrue(ex instanceof MoveCommandException);
        }
    }
}