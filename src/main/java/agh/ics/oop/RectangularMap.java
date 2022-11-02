package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(width, height);
    }
    private ArrayList<Animal> animals = new ArrayList<>();

    public Animal getAnimal(int i) { return this.animals.get(i); }
    public int getAnimalsNum() { return this.animals.size(); }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position)) && (position.follows(this.lowerLeft) && position.precedes(this.upperRight));
    }

    @Override
    public boolean place(Animal animal) {
        if (animal.getPosition().follows(this.lowerLeft) && animal.getPosition().precedes(this.upperRight)) {
            if (!isOccupied(animal.getPosition())) {
                this.animals.add(animal);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(this.lowerLeft, this.upperRight);
    }
}
