package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        upperRight = new Vector2d(width, height);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean animalOcc = super.canMoveTo(position);
        if (animalOcc) {
            return (position.follows(lowerLeft) && position.precedes(upperRight));
        }
        return false;
    }
    protected Vector2d findLowerLeft() {
        return this.lowerLeft;
    }
    protected Vector2d findUpperRight() {
        return this.upperRight;
    }
}
