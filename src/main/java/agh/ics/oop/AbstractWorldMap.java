package agh.ics.oop;

import java.util.ArrayList;

abstract public class AbstractWorldMap implements IWorldMap {
    protected ArrayList<Animal> animals = new ArrayList<>();

    public Animal getAnimal(int i) { return animals.get(i); }
    public int getAnimalsNum() { return animals.size(); }

    private boolean animalAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    abstract protected Vector2d findLowerLeft();
    abstract protected Vector2d findUpperRight();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!animalAt(position));
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (animalAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString() {
        MapVisualizer mapString = new MapVisualizer(this);
        Vector2d lowerLeft = findLowerLeft();
        Vector2d upperRight = findUpperRight();
        return mapString.draw(lowerLeft, upperRight);
    }
}
