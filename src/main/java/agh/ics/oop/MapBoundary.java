package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private TreeSet<Object> alongXAxis = new TreeSet<>((o1, o2) -> {
        if (o1 instanceof Grass && o2 instanceof Grass) {
            if (((Grass) o1).getPosition().x == ((Grass) o2).getPosition().x) {
                return Integer.compare(((Grass) o1).getPosition().y, ((Grass) o2).getPosition().y);
            } else return Integer.compare(((Grass) o1).getPosition().x, ((Grass) o2).getPosition().x);
        }
        if (o1 instanceof Animal && o2 instanceof Animal) {
            if (((Animal) o1).getPosition().x == ((Animal) o2).getPosition().x) {
                return Integer.compare(((Animal) o1).getPosition().y, ((Animal) o2).getPosition().y);
            } else return Integer.compare(((Animal) o1).getPosition().x, ((Animal) o2).getPosition().x);
        }
        if (o1 instanceof Grass && o2 instanceof Animal) {
            if (((Grass) o1).getPosition().x == ((Animal) o2).getPosition().x) {
                if (((Grass) o1).getPosition().y == ((Animal) o2).getPosition().y) {
                    return -1;
                } else return Integer.compare(((Grass) o1).getPosition().y, ((Animal) o2).getPosition().y);
            } else return Integer.compare(((Grass) o1).getPosition().x, ((Animal) o2).getPosition().x);
        }
        if (o1 instanceof Animal && o2 instanceof Grass) {
            if (((Animal) o1).getPosition().x == ((Grass) o2).getPosition().x) {
                if (((Animal) o1).getPosition().y == ((Grass) o2).getPosition().y) {
                    return 1;
                } else return Integer.compare(((Animal) o1).getPosition().y, ((Grass) o2).getPosition().y);
            } else return Integer.compare(((Animal) o1).getPosition().x, ((Grass) o2).getPosition().x);
        }
        return 0;
    });
    private TreeSet<Object> alongYAxis = new TreeSet<>((o1, o2) -> {
        if (o1 instanceof Grass && o2 instanceof Grass) {
            if (((Grass) o1).getPosition().y == ((Grass) o2).getPosition().y) {
                return Integer.compare(((Grass) o1).getPosition().x, ((Grass) o2).getPosition().x);
            } else return Integer.compare(((Grass) o1).getPosition().y, ((Grass) o2).getPosition().y);
        }
        if (o1 instanceof Animal && o2 instanceof Animal) {
            if (((Animal) o1).getPosition().y == ((Animal) o2).getPosition().y) {
                return Integer.compare(((Animal) o1).getPosition().x, ((Animal) o2).getPosition().x);
            } else return Integer.compare(((Animal) o1).getPosition().y, ((Animal) o2).getPosition().y);
        }
        if (o1 instanceof Grass && o2 instanceof Animal) {
            if (((Grass) o1).getPosition().y == ((Animal) o2).getPosition().y) {
                if (((Grass) o1).getPosition().x == ((Animal) o2).getPosition().x) {
                    return -1;
                } else return Integer.compare(((Grass) o1).getPosition().x, ((Animal) o2).getPosition().x);
            } else return Integer.compare(((Grass) o1).getPosition().y, ((Animal) o2).getPosition().y);
        }
        if (o1 instanceof Animal && o2 instanceof Grass) {
            if (((Animal) o1).getPosition().y == ((Grass) o2).getPosition().y) {
                if (((Animal) o1).getPosition().x == ((Grass) o2).getPosition().x) {
                    return 1;
                } else return Integer.compare(((Animal) o1).getPosition().x, ((Grass) o2).getPosition().x);
            } else return Integer.compare(((Animal) o1).getPosition().y, ((Grass) o2).getPosition().y);
        }
        return 0;
    });
    public void add(Object o) {
        alongXAxis.add(o);
        alongYAxis.add(o);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Iterator<Object> itX = alongXAxis.iterator();
        Iterator<Object> itY = alongYAxis.iterator();
        while (itX.hasNext()) {
            Object o = itX.next();
            if (o instanceof Animal && ((Animal) o).getPosition().equals(newPosition)) {
                itX.remove();
                alongXAxis.add(o);
                break;
            }
        }
        while (itY.hasNext()) {
            Object o = itY.next();
            if (o instanceof Animal && ((Animal) o).getPosition().equals(newPosition)) {
                itY.remove();
                alongYAxis.add(o);
                break;
            }
        }
    }

    public Vector2d lowerLeft() {
        Object objectX = alongXAxis.first();
        Object objectY = alongYAxis.first();
        return getVector2d(objectX, objectY);
    }

    public Vector2d upperRight() {
        Object objectX = alongXAxis.last();
        Object objectY = alongYAxis.last();
        return getVector2d(objectX, objectY);
    }

    private Vector2d getVector2d(Object objectX, Object objectY) {
        if (objectX instanceof Grass) {
            if (objectY instanceof Grass) {
                return new Vector2d(((Grass) objectX).getPosition().x, ((Grass) objectY).getPosition().y);
            }
            if (objectY instanceof Animal) {
                return new Vector2d(((Grass) objectX).getPosition().x, ((Animal) objectY).getPosition().y);
            }
        }
        if (objectX instanceof Animal) {
            if (objectY instanceof Grass) {
                return new Vector2d(((Animal) objectX).getPosition().x, ((Grass) objectY).getPosition().y);
            }
            if (objectY instanceof Animal) {
                return new Vector2d(((Animal) objectX).getPosition().x, ((Animal) objectY).getPosition().y);
            }
        }
        return null;
    }

}
