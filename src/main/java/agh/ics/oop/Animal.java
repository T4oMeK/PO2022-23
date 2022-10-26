package agh.ics.oop;

import java.util.ArrayList;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;

    public String toString() {
        return position.toString() + " " + MapDirection.toString(this.orientation);
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case LEFT -> this.orientation = MapDirection.previous(this.orientation);
            case RIGHT -> this.orientation = MapDirection.next(this.orientation);
            case FORWARD -> {
                Vector2d newpos = this.position.add(MapDirection.toUnitVector(this.orientation));
                if (newpos.precedes(new Vector2d(4, 4)) && newpos.follows(new Vector2d(0, 0))) {
                    this.position = newpos;
                }
            }
            case BACKWARD -> {
                Vector2d newpos = this.position.subtract(MapDirection.toUnitVector(this.orientation));
                if (newpos.precedes(new Vector2d(4, 4)) && newpos.follows(new Vector2d(0, 0))) {
                    this.position = newpos;
                }
            }
        }
    }
}
