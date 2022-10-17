package agh.ics.oop;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean precedes(Vector2d other) {
        return (this.x <= other.x && this.y <= other.y);
    }

    public boolean follows(Vector2d other) {
        return (this.x >= other.x && this.y >= other.y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        if (other.x > this.x) {
            if (other.y > this.y) {
                return new Vector2d(other.x, other.y);
            }
            else return new Vector2d(other.x, this.y);
        }
        else {
            if (other.y > this.y) {
                return new Vector2d(this.x, other.y);
            }
            else return new Vector2d(this.x, this.y);
        }
    }

    public Vector2d lowerLeft(Vector2d other) {
        if (other.x < this.x) {
            if (other.y < this.y) {
                return new Vector2d(other.x, other.y);
            }
            else return new Vector2d(other.x, this.y);
        }
        else {
            if (other.y < this.y) {
                return new Vector2d(this.x, other.y);
            }
            else return new Vector2d(this.x, this.y);
        }
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other) {

    }
}
