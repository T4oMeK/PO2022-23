package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private ArrayList<Grass> patches = new ArrayList<>();
    private int range;
    public GrassField(int tiles) {
        range = ((int) Math.floor(Math.sqrt(tiles*10)));
        for (int i = 0; i < tiles;) {
            Vector2d toAdd = new Vector2d(ThreadLocalRandom.current().nextInt(0, range), ThreadLocalRandom.current().nextInt(0, range));
            if (!isOccupied(toAdd)) {
                patches.add(new Grass(toAdd));
                ++i;
            }
        }
    }

    // for testing purposes
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

    protected Vector2d findLowerLeft() {
        Vector2d lowerLeft = new Vector2d(range, range);
        for (Animal animal: animals) {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
        }
        for (Grass patch: this.patches) {
            lowerLeft = lowerLeft.lowerLeft(patch.getPosition());
        }
        return lowerLeft;
    }

    protected Vector2d findUpperRight() {
        Vector2d upperRight = new Vector2d(0, 0);
        for (Animal animal: animals) {
            upperRight = upperRight.upperRight(animal.getPosition());
        }
        for (Grass patch: this.patches) {
            upperRight = upperRight.upperRight(patch.getPosition());
        }
        return upperRight;
    }
}
