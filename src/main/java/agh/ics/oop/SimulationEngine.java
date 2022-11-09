package agh.ics.oop;

import org.w3c.dom.css.Rect;

import java.util.LinkedList;

public class SimulationEngine implements IEngine {
    private IWorldMap map;
    private LinkedList<MoveDirection> dirs;
    private Vector2d[] initialPositions;
    public SimulationEngine(LinkedList<MoveDirection> dirs, IWorldMap map, Vector2d[] initialPositions) {
        this.dirs = dirs;
        this.map = map;
        this.initialPositions = initialPositions;
    }
    @Override
    public void run() {
        for (Vector2d position: initialPositions) {
            this.map.place(new Animal(this.map, position));
        }
        int n = ((AbstractWorldMap) this.map).getAnimalsNum();
        if (n != 0) {
            int i = 0;
            for (MoveDirection dir : dirs) {
                ((AbstractWorldMap) this.map).getAnimal(i).move(dir);
                i = (i+1) % n;
            }
        }
        System.out.println(this.map);
    }
}
