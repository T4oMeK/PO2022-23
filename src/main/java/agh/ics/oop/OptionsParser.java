package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static LinkedList<MoveDirection> parse(List<String> args) throws IllegalArgumentException {
        LinkedList<MoveDirection> list = new LinkedList<>();
        for (String arg : args) {
            switch (arg) {
                case "b", "backward" -> list.add(MoveDirection.BACKWARD);
                case "f", "forward" -> list.add(MoveDirection.FORWARD);
                case "l", "left" -> list.add(MoveDirection.LEFT);
                case "r", "right" -> list.add(MoveDirection.RIGHT);
                default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
            }
        }
        return list;
    }
}
