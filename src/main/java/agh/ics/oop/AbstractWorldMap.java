package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    public Animal getAnimal(int i) { return animals.get(i); }
    public int getAnimalsNum() { return animals.size(); }

    abstract protected Vector2d findLowerLeft();
    abstract protected Vector2d findUpperRight();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition, animal);
        animals.put(newPosition, animal);
    }
    public String toString() {
        MapVisualizer mapString = new MapVisualizer(this);
        Vector2d lowerLeft = findLowerLeft();
        Vector2d upperRight = findUpperRight();
        return mapString.draw(lowerLeft, upperRight);
    }
}
