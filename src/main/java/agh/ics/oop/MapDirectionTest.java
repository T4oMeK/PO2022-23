package agh.ics.oop;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class MapDirectionTest {
    @Test
    public static void verNext() {
        assertEquals(MapDirection.next(MapDirection.NORTH), MapDirection.EAST);
        assertEquals(MapDirection.next(MapDirection.EAST), MapDirection.SOUTH);
        assertEquals(MapDirection.next(MapDirection.SOUTH), MapDirection.WEST);
        assertEquals(MapDirection.next(MapDirection.WEST), MapDirection.NORTH);
    }
    @Test
    public static void verPrevious() {
        assertEquals(MapDirection.previous(MapDirection.NORTH), MapDirection.WEST);
        assertEquals(MapDirection.previous(MapDirection.EAST), MapDirection.NORTH);
        assertEquals(MapDirection.previous(MapDirection.SOUTH), MapDirection.EAST);
        assertEquals(MapDirection.previous(MapDirection.WEST), MapDirection.SOUTH);
    }
}
