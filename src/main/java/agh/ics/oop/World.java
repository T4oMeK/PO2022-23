package agh.ics.oop;
import static java.lang.System.out;

public class World {
    public static void run(Direction[] dirs) {
        for(Direction argument: java.util.Arrays.copyOfRange(dirs, 0, dirs.length - 1)) {
            out.print(argument + ", ");
        }
        out.println(dirs[dirs.length - 1]);
        for(Direction argument: dirs) {
            if (argument != null) {
                switch (argument) {
                    case FORWARD -> out.println("Zwierzak idzie do przodu");
                    case BACKWARD -> out.println("Zwierzak idzie do tylu");
                    case LEFT -> out.println("Zwierzak skreca w lewo");
                    case RIGHT -> out.println("Zwierzak skreca w prawo");
                }
            }
        }
    }
    public static Direction[] toDirection(String[] args){
        Direction[] dirArgs = new Direction[args.length];

        for (int i = 0; i < args.length; ++i) {
            Direction conv;
            switch (args[i]) {
                case "f" -> conv = Direction.FORWARD;
                case "b" -> conv = Direction.BACKWARD;
                case "l" -> conv = Direction.LEFT;
                case "r" -> conv = Direction.RIGHT;
                default -> conv = null;
            }
                dirArgs[i] = conv;
        }
        return dirArgs;
    }
    public static void main(String[] args) {
        out.println("Start");
        run(toDirection(args));
        out.println("Stop");
    }
}


