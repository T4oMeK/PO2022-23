package agh.ics.oop;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class Vector2dTest {
    @Test
    public void verEquals() {
        Vector2d testv = new Vector2d(2, 5);
        Object test1 = new Vector2d(1, 1);
        Object test2 = new Vector2d(2, 5);
        Object test3 = new Vector2d(-15, 2222);
        assertFalse(testv.equals(test1));
        assertTrue(testv.equals(test2));
        assertFalse(testv.equals(test3));
    }
    @Test
    public void verToString() {
        Vector2d test1 = new Vector2d(-123, 321);
        Vector2d test2 = new Vector2d(0, 0);
        assertEquals(test1.toString(), "(-123, 321)");
        assertEquals(test2.toString(), "(0, 0)");
    }
    @Test
    public void verPrecedes() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        Vector2d test5 = new Vector2d(-3, -6);
        Vector2d test6 = new Vector2d(5, 6);
        assertFalse(test2.precedes(test1));
        assertFalse(test3.precedes(test1));
        assertFalse(test4.precedes(test1));
        assertTrue(test5.precedes(test1));
        assertTrue(test6.precedes(test1));
    }
    @Test
    public void verFollows() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        Vector2d test5 = new Vector2d(-3, -6);
        Vector2d test6 = new Vector2d(5, 6);
        assertFalse(test2.follows(test1));
        assertFalse(test3.follows(test1));
        assertTrue(test4.follows(test1));
        assertFalse(test5.follows(test1));
        assertTrue(test6.follows(test1));
    }
    @Test
    public void verUR() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        assertEquals(test1.upperRight(test2), new Vector2d(5, 9));
        assertEquals(test1.upperRight(test3), new Vector2d(10, 6));
        assertEquals(test1.upperRight(test4), new Vector2d(10, 15));
    }
    @Test
    public void verLL() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(-3, -6);
        assertEquals(test1.lowerLeft(test2), new Vector2d(1, 6));
        assertEquals(test1.lowerLeft(test3), new Vector2d(5, 2));
        assertEquals(test1.lowerLeft(test4), new Vector2d(-3, -6));
    }
    @Test
    public void verAdd() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        Vector2d test5 = new Vector2d(-3, -6);
        Vector2d test6 = new Vector2d(5, 6);
        assertEquals(test1.add(test2), new Vector2d(6, 15));
        assertEquals(test1.add(test3), new Vector2d(15, 8));
        assertEquals(test1.add(test4), new Vector2d(15, 21));
        assertEquals(test1.add(test5), new Vector2d(2, 0));
        assertEquals(test1.add(test6), new Vector2d(10, 12));
    }
    @Test
    public void verSubtract() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        Vector2d test5 = new Vector2d(-3, -6);
        Vector2d test6 = new Vector2d(5, 6);
        assertEquals(test1.subtract(test2), new Vector2d(4, -3));
        assertEquals(test1.subtract(test3), new Vector2d(-5, 4));
        assertEquals(test1.subtract(test4), new Vector2d(-5, -9));
        assertEquals(test1.subtract(test5), new Vector2d(8, 12));
        assertEquals(test1.subtract(test6), new Vector2d(0, 0));
    }
    @Test
    public void verOpposite() {
        Vector2d test1 = new Vector2d(5, 6);
        Vector2d test2 = new Vector2d(1, 9);
        Vector2d test3 = new Vector2d(10, 2);
        Vector2d test4 = new Vector2d(10, 15);
        Vector2d test5 = new Vector2d(-3, -6);
        Vector2d test6 = new Vector2d(5, 6);
        assertEquals(test1.opposite(), new Vector2d(-5, -6));
        assertEquals(test2.opposite(), new Vector2d(-1, -9));
        assertEquals(test3.opposite(), new Vector2d(-10, -2));
        assertEquals(test4.opposite(), new Vector2d(-10, -15));
        assertEquals(test5.opposite(), new Vector2d(3, 6));
        assertEquals(test6.opposite(), new Vector2d(-5, -6));
    }
}
