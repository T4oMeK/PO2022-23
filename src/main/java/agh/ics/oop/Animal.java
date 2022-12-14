package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal implements IPositionChangeObserver, IMapElement {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    private List<IPositionChangeObserver> observers = new ArrayList<>();
    public Animal() {}
    public Animal(IWorldMap map) {
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

    public String toString() { return MapDirection.toString(this.orientation); }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
    public MapDirection getOrientation() {
        return this.orientation;
    }
    public Vector2d getPosition() { return this.position; }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d newpos = null;
        switch(direction) {
            case LEFT -> this.orientation = MapDirection.previous(this.orientation);
            case RIGHT -> this.orientation = MapDirection.next(this.orientation);
            case FORWARD -> {
                newpos = this.position.add(Objects.requireNonNull(MapDirection.toUnitVector(this.orientation)));
            }
            case BACKWARD -> {
                newpos = this.position.subtract(Objects.requireNonNull(MapDirection.toUnitVector(this.orientation)));
            }
        }

        if ((newpos != null) && (this.map != null) && this.map.canMoveTo(newpos)) {
            this.position = newpos;
        }
        if ((newpos != null) && this.map == null) {
            this.position = newpos;
        }
    }

    public String toImage() {
        return switch(orientation) {
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
        };
    }

    public String toLabel() {
        return "Z (" + position.x + ", " + position.y + ")";
    }
}