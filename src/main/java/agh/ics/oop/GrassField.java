package agh.ics.oop;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private ArrayList<Grass> patches = new ArrayList<>();
    private MapBoundary objects = new MapBoundary();
    private int range;
    public GrassField(int tiles) {
        range = ((int) Math.floor(Math.sqrt(tiles*10)));
        for (int i = 0; i < tiles;) {
            Vector2d toAdd = new Vector2d(ThreadLocalRandom.current().nextInt(0, range), ThreadLocalRandom.current().nextInt(0, range));
            if (!isOccupied(toAdd)) {
                Grass patch = new Grass(toAdd);
                patches.add(patch);
                objects.add(patch);
                ++i;
            }
        }
    }

    public Grass getPatch(int n) { return patches.get(n); }
    @Override
    public boolean isOccupied(Vector2d position) {
        boolean animalOcc = super.isOccupied(position);
        if (!animalOcc) {
            for (Grass patch: patches) {
                if (patch.getPosition().equals(position)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);
        objects.positionChanged(oldPosition, newPosition);
    }
    @Override
    public void place(Animal animal) {
        super.place(animal);
        objects.add(animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object == null)
            for (Grass patch: patches) {
                if (patch.getPosition().equals(position)) {
                    return patch;
                }
            }
        return object;
    }

    public Vector2d findLowerLeft() {
        return objects.lowerLeft();
    }

    public Vector2d findUpperRight() {
        return objects.upperRight();
    }
}
