package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    public static String toString(MapDirection dir) {
        String res;
        switch(dir) {
            case NORTH -> res = "N";
            case SOUTH -> res = "S";
            case EAST -> res = "E";
            case WEST -> res = "W";
            default -> res = "";
        }
        return res;
    }
    public static MapDirection next(MapDirection dir) {
        MapDirection res;
        switch(dir) {
            case NORTH -> res = MapDirection.EAST;
            case SOUTH -> res = MapDirection.WEST;
            case EAST -> res = MapDirection.SOUTH;
            case WEST -> res = MapDirection.NORTH;
            default -> res = null;
        }
        return res;
    }
    public static MapDirection previous(MapDirection dir) {
        MapDirection res;
        switch(dir) {
            case NORTH -> res = MapDirection.WEST;
            case SOUTH -> res = MapDirection.EAST;
            case EAST -> res = MapDirection.NORTH;
            case WEST -> res = MapDirection.SOUTH;
            default -> res = null;
        }
        return res;
    }
    public static Vector2d toUnitVector(MapDirection dir) {
        switch(dir) {
            case NORTH -> {
                return new Vector2d(0, 1);
            }
            case SOUTH -> {
                return new Vector2d(0, -1);
            }
            case EAST -> {
                return new Vector2d(1, 0);
            }
            case WEST -> {
                return new Vector2d(-1, 0);
            }
        }
        return null;
    }
}
