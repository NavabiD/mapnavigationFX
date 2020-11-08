package com.dariush.mapnav;

import java.io.IOException;

public class Movement {

    private RelativeDirection relativeDirection;
    private int steps;

    public Movement(String mvCmd ) throws MoveCommandException {
        char mvCmdRelDir = mvCmd.charAt(0);
        switch (mvCmdRelDir) {
            case 'R' :
                this.relativeDirection=RelativeDirection.RIGHT;
                break;
            case 'L' :
                this.relativeDirection=RelativeDirection.LEFT;
                break;
            default:
                MoveCommandException e = new MoveCommandException("Invalid move command.");
                throw (e);
        }
        this.steps = Integer.parseInt(mvCmd.substring(1, mvCmd.length()));
    }

    public RelativeDirection getRelativeDirection() {
        return relativeDirection;
    }

    public int getSteps() {
        return steps;
    }
}
