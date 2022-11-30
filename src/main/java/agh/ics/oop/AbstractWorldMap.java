package agh.ics.oop;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    public Map<Vector2d, Animal> animals = new HashMap<>();

    public Animal getAnimal (Vector2d animalPos) { return animals.get(animalPos); }
    public ArrayList<Animal> getAnimals() { return new ArrayList<>(animals.values()); }

    abstract public Vector2d findLowerLeft();
    abstract public Vector2d findUpperRight();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
        }
        else {
            throw new IllegalArgumentException("Animal has been already placed at position " + position + " before or is out of bounds");
        }
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
