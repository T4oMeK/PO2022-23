package agh.ics.oop;

import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.*;

public class MapTests {
    @Test
    public void verRectangularMap() {
        IWorldMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(map, new Vector2d(4,4));
        Animal animal2 = new Animal(map, new Vector2d(3, 4));
        Animal animal3 = new Animal(map, new Vector2d(6, 7));
        map.place(animal1);
        map.place(animal2);
        assertThrows(IllegalArgumentException.class, () -> map.place(animal3));
        assertTrue(map.isOccupied(new Vector2d(4,4)));
        assertTrue(map.isOccupied(new Vector2d(3,4)));
        assertFalse(map.isOccupied(new Vector2d(6,7)));
        assertTrue(map.canMoveTo(new Vector2d(4,3)));
        assertFalse(map.canMoveTo(new Vector2d(3,4)));
        assertTrue(map.canMoveTo(new Vector2d(2,1)));
        assertFalse(map.canMoveTo(new Vector2d(5,4)));
        assertTrue(map.objectAt(new Vector2d(4,4)) instanceof Animal);
        assertTrue(map.objectAt(new Vector2d(3,4)) instanceof Animal);
        assertNull(map.objectAt(new Vector2d(2, 1)));
    }
    @Test
    public void verGrassField() {
        IWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(map, new Vector2d(-5,-20));
        Animal animal2 = new Animal(map, new Vector2d(-7000, 4000));
        Animal animal3 = new Animal(map, new Vector2d(31250, 44444));
        Vector2d patchPosition = ((GrassField) map).getPatch(2).getPosition();
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        assertThrows(IllegalArgumentException.class, () -> map.place(animal3));
        assertTrue(map.isOccupied(new Vector2d(-5,-20)));
        assertTrue(map.isOccupied(new Vector2d(-7000,4000)));
        assertFalse(map.isOccupied(new Vector2d(-4,-567)));
        assertTrue(map.isOccupied(patchPosition));
        assertTrue(map.canMoveTo(new Vector2d(2222,3333)));
        assertFalse(map.canMoveTo(new Vector2d(-5,-20)));
        assertTrue(map.canMoveTo(new Vector2d(-13245,32645)));
        assertFalse(map.canMoveTo(new Vector2d(-7000,4000)));
        assertTrue(map.canMoveTo(patchPosition));
        assertTrue(map.objectAt(new Vector2d(-5,-20)) instanceof Animal);
        assertTrue(map.objectAt(new Vector2d(31250,44444)) instanceof Animal);
        assertTrue(map.objectAt(patchPosition) instanceof Grass);
        assertNull(map.objectAt(new Vector2d(-47, -2645)));
    }
}
