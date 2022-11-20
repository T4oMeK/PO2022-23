package agh.ics.oop;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationEngine implements IEngine {
    private IWorldMap map;
    private LinkedList<MoveDirection> dirs;
    private Vector2d[] initialPositions;
    private ArrayList<Animal> animals = new ArrayList<>();
    public SimulationEngine(LinkedList<MoveDirection> dirs, IWorldMap map, Vector2d[] initialPositions) {
        this.dirs = dirs;
        this.map = map;
        this.initialPositions = initialPositions;
    }
    @Override
    public void run() {
        for (Vector2d position: initialPositions) {
            Animal animal = new Animal(this.map, position);
            animal.addObserver((AbstractWorldMap) this.map);
            this.map.place(animal);
            animals.add(animal);
        }
        int n = animals.size();
        if (n != 0) {
            int i = 0;
            for (MoveDirection dir : dirs) {
                Vector2d oldPos = animals.get(i).getPosition();
                this.animals.get(i).move(dir);
                Vector2d newPos = animals.get(i).getPosition();
                animals.get(i).positionChanged(oldPos, newPos);
                i = (i+1) % n;
            }
        }
        System.out.println(this.map);
    }
}
