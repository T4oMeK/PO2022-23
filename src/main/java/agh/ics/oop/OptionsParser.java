package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(String[] args) {
        ArrayList<MoveDirection> list = new ArrayList<MoveDirection>();
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
