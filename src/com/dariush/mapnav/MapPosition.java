package com.dariush.mapnav;

import java.util.HashMap;


public class MapPosition {

    private CardinalDirection cardinalDirection;
    private HashMap <CardinalDirection, Integer> coordinate = new HashMap<>();

    public MapPosition() {

        this.cardinalDirection = CardinalDirection.NORTH;
        this.coordinate.put(CardinalDirection.NORTH, 0);
        this.coordinate.put(CardinalDirection.SOUTH, 0);
        this.coordinate.put(CardinalDirection.EAST, 0);
        this.coordinate.put(CardinalDirection.WEST, 0);
    }

    public MapPosition move(Movement movement) {

        this.changeCardinalDirection(movement.getRelativeDirection());
        this.coordinate.put(this.cardinalDirection,
                coordinate.get(this.cardinalDirection) + movement.getSteps());
        return this;
    }

    public int distanceFromOrigin() {
        return Math.abs(coordinate.get(CardinalDirection.NORTH) - coordinate.get(CardinalDirection.SOUTH)) +
                Math.abs(coordinate.get(CardinalDirection.EAST) - coordinate.get(CardinalDirection.WEST));

    }

    private void changeCardinalDirection (RelativeDirection relDirection ) {
        switch  (this.cardinalDirection) {
            case NORTH:
                if (relDirection == RelativeDirection.RIGHT)
                    this.cardinalDirection = CardinalDirection.EAST;
                else // relDirection == RelativeDirection.LEFT
                    this.cardinalDirection = CardinalDirection.WEST;
                break;

            case SOUTH:
                if (relDirection == RelativeDirection.RIGHT)
                    this.cardinalDirection  = CardinalDirection.WEST;
                else // relDirection == RelativeDirection.LEFT
                    this.cardinalDirection  = CardinalDirection.EAST;
                break;

            case EAST:
                if (relDirection == RelativeDirection.RIGHT)
                    this.cardinalDirection  = CardinalDirection.SOUTH;
                else // relDirection == RelativeDirection.LEFT
                    this.cardinalDirection  = CardinalDirection.NORTH;
                break;

            default: // this.cardinalDirection = WEST
                if (relDirection == RelativeDirection.RIGHT)
                    this.cardinalDirection  = CardinalDirection.NORTH;
                else // relDirection == RelativeDirection.LEFT
                    this.cardinalDirection  = CardinalDirection.SOUTH;
        }
    }
}
