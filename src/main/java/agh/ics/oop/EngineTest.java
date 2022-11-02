package agh.ics.oop;

import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.assertEquals;

public class EngineTest {
    @Test
    public static void verRun() {
        String[] args1 = {"f", "b", "r", "l"};
        String[] args2 = {"f", "f", "r", "r"};
        String[] args3 = {"f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> directions1 = OptionsParser.parse(args1);
        LinkedList<MoveDirection> directions2 = OptionsParser.parse(args2);
        LinkedList<MoveDirection> directions3 = OptionsParser.parse(args3);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        Vector2d[] none = {};
        IEngine engine1 = new SimulationEngine(directions1, map, positions);
        engine1.run();
        Vector2d pos1 = new Vector2d(2, 3);
        Vector2d pos2 = new Vector2d(3, 3);
        assertEquals(((RectangularMap) map).getAnimal(0).getPosition(), pos1);
        assertEquals(((RectangularMap) map).getAnimal(1).getPosition(), pos2);
        assertEquals(((RectangularMap) map).getAnimal(0).getOrientation(), MapDirection.EAST);
        assertEquals(((RectangularMap) map).getAnimal(1).getOrientation(), MapDirection.WEST);
        IEngine engine2 = new SimulationEngine(directions2, map, none);
        engine2.run();
        assertEquals(((RectangularMap) map).getAnimal(0).getPosition(), pos1);
        assertEquals(((RectangularMap) map).getAnimal(1).getPosition(), pos2);
        assertEquals(((RectangularMap) map).getAnimal(0).getOrientation(), MapDirection.SOUTH);
        assertEquals(((RectangularMap) map).getAnimal(1).getOrientation(), MapDirection.NORTH);
        pos1 = new Vector2d(2, 0);
        pos2 = new Vector2d(3, 5);
        IEngine engine3 = new SimulationEngine(directions3, map, none);
        engine3.run();
        assertEquals(((RectangularMap) map).getAnimal(0).getPosition(), pos1);
        assertEquals(((RectangularMap) map).getAnimal(1).getPosition(), pos2);
        assertEquals(((RectangularMap) map).getAnimal(0).getOrientation(), MapDirection.SOUTH);
        assertEquals(((RectangularMap) map).getAnimal(1).getOrientation(), MapDirection.NORTH);
    }
}
