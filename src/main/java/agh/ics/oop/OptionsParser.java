package agh.ics.oop;

import java.util.LinkedList;

public class OptionsParser {
    public static LinkedList<MoveDirection> parse(String[] args) {
        LinkedList<MoveDirection> list = new LinkedList<>();
        for (String arg : args) {
            switch (arg) {
                case "b", "backward" -> list.add(MoveDirection.BACKWARD);
                case "f", "forward" -> list.add(MoveDirection.FORWARD);
                case "l", "left" -> list.add(MoveDirection.LEFT);
                case "r", "right" -> list.add(MoveDirection.RIGHT);
            }
        }
        return list;
    }
}
