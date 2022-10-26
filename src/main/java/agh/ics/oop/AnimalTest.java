package agh.ics.oop;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class AnimalTest {
    @Test
    public static void verParse() {
        String[] args = {"f", "r", "right", "roght", "lefr", "backward", "b", "f", "forward",
                "forward", "f", "r", "f", "f", "f", "l", "r", "l", "l"};
        ArrayList<MoveDirection> dirs = OptionsParser.parse(args);
        ArrayList<MoveDirection> exp = new ArrayList<MoveDirection>();
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
        ArrayList<MoveDirection> dirs = OptionsParser.parse(args);
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
        ArrayList<MoveDirection> dirs1 = OptionsParser.parse(args1);
        ArrayList<MoveDirection> dirs2 = OptionsParser.parse(args2);
        ArrayList<MoveDirection> dirs3 = OptionsParser.parse(args3);
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
        Animal cat = new Animal();
        String[] args = {"f", "f", "f", "f", "l", "f", "f", "f", "f", "l", "f", "f", "f", "f", "f", "f"};
        ArrayList<MoveDirection> dirs = OptionsParser.parse(args);
        for (MoveDirection dir : dirs) {
            cat.move(dir);
        }
        assertTrue(cat.isAt(new Vector2d(0, 0)));
    }
}
