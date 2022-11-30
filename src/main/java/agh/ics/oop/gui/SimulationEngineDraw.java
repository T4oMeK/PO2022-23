package agh.ics.oop.gui;

import agh.ics.oop.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngineDraw implements IEngine, Runnable {
    int moveDelay;
    private List<IRenderObserver> observers = new ArrayList<>();
    private LinkedList<MoveDirection> directions;
    private List<Animal> animals = new ArrayList<>();

    public SimulationEngineDraw(IWorldMap map, Vector2d[] positions, int delay) {
        for (Vector2d position: positions) {
            if (map.canMoveTo(position)) {
                Animal animal = new Animal(map, position);
                map.place(animal);
                this.animals.add(animal);
                animal.addObserver((IPositionChangeObserver) map);
            }
            else {
                if (map.objectAt(position) instanceof Grass) {
                    Animal animal = new Animal(map, position);
                    map.place(animal);
                    this.animals.add(animal);
                    animal.addObserver((IPositionChangeObserver) map);
                }
            }
        }

        this.moveDelay = delay;
    }

    public SimulationEngineDraw(LinkedList<MoveDirection> directions, IWorldMap map, Vector2d[] positions, int delay) {
        this(map, positions, delay);
        setDirections(directions);
    }
    public void setDirections(LinkedList<MoveDirection> directions) {
        this.directions = directions;
    }
    public void addObserver(IRenderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IRenderObserver observer) {
        observers.remove(observer);
    }

    public void render() {
        for (IRenderObserver observer: observers) {
            observer.render();
        }
    }
    @Override
    public void run() {
        int n = this.animals.size();
        for (int i = 0; i < this.directions.size(); ++i) {
            Vector2d oldPosition = this.animals.get(i % n).getPosition();
            this.animals.get(i % n).move(this.directions.get(i));
            Vector2d newPosition = this.animals.get(i % n).getPosition();
            this.animals.get(i % n).positionChanged(oldPosition, newPosition);
            render();
            try {
                Thread.sleep(this.moveDelay);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
