package agh.ics.oop;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class AnimalTest {
    @Test
    public static void verParse() {
        String[] args = {"f", "r", "right", "roght", "lefr", "backward", "b", "f", "forward",
                "forward", "f", "r", "f", "f", "f", "l", "r", "l", "l"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(List.of(args)));
        String[] argsGood = {"f", "r", "right", "backward", "b", "f", "forward",
                "forward", "f", "r", "f", "f", "f", "l", "r", "l", "l"};
        LinkedList<MoveDirection> dirs = OptionsParser.parse(List.of(argsGood));
        LinkedList<MoveDirection> exp = new LinkedList<>();
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.RIGHT);
        exp.add(MoveDirection.RIGHT);
        exp.add(MoveDirection.BACKWARD);
        exp.add(MoveDirection.BACKWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.RIGHT);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.FORWARD);
        exp.add(MoveDirection.LEFT);
        exp.add(MoveDirection.RIGHT);
        exp.add(MoveDirection.LEFT);
        exp.add(MoveDirection.LEFT);
        assertEquals(dirs, exp);
    }

    @Test
    public void verOrientation() {
        Animal dog = new Animal();
        String[] args = {"r", "f", "f", "f", "l", "r", "l", "l", "left"};
        LinkedList<MoveDirection> dirs = OptionsParser.parse(List.of(args));
        MapDirection[] faces = {MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST,
        MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH};
        int i = 0;
        for(MoveDirection dir: dirs) {
            dog.move(dir);
            assertEquals(dog.getOrientation(), faces[i]);
            i += 1;
        }
    }

    @Test
    public void verPosition() {
        Animal dog = new Animal();
        String[] args1 = {"f", "f", "r", "f"};
        String[] args2 = {"b", "l", "l", "f"};
        String[] args3 = {"r", "b", "b"};
        LinkedList<MoveDirection> dirs1 = OptionsParser.parse(List.of(args1));
        LinkedList<MoveDirection> dirs2 = OptionsParser.parse(List.of(args2));
        LinkedList<MoveDirection> dirs3 = OptionsParser.parse(List.of(args3));
        for(MoveDirection dir: dirs1) {
            dog.move(dir);
        }
        assertTrue(dog.isAt(new Vector2d(3, 4)));
        for(MoveDirection dir: dirs2) {
            dog.move(dir);
        }
        assertTrue(dog.isAt(new Vector2d(1, 4)));
        for(MoveDirection dir: dirs3) {
            dog.move(dir);
        }
        assertTrue(dog.isAt(new Vector2d(1, 2)));
    }

    @Test
    public void verBorders() {
        Animal cat = new Animal(new RectangularMap(5, 5));
        String[] args = {"f", "f", "f", "f", "l", "f", "f", "f", "f", "l", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> dirs = OptionsParser.parse(List.of(args));
        for (MoveDirection dir : dirs) {
            cat.move(dir);
        }
        assertTrue(cat.isAt(new Vector2d(0, 0)));
    }
}
