package agh.ics.oop;

import org.w3c.dom.css.Rect;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.LinkedList;

public class World {
    public static void run(Direction[] dirs) {
        for(Direction argument: java.util.Arrays.copyOfRange(dirs, 0, dirs.length - 1)) {
            out.print(argument + ", ");
        }
        out.println(dirs[dirs.length - 1]);
        for(Direction argument: dirs) {
            if (argument != null) {
                switch (argument) {
                    case FORWARD -> out.println("Zwierzak idzie do przodu");
                    case BACKWARD -> out.println("Zwierzak idzie do tylu");
                    case LEFT -> out.println("Zwierzak skreca w lewo");
                    case RIGHT -> out.println("Zwierzak skreca w prawo");
                }
            }
        }
    }
    public static void main(String[] args) {
        out.println("Start");
        LinkedList<MoveDirection> directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println("Stop");
    }
}


