package pattern.factory;

/**
 * The Factory Method design pattern is used instead of the regular class constructor for keeping within the SOLID principles of programming,
 * decoupling the construction of objects from the objects themselves
 */
public class FactoryMethod {
    public static void main(String[] args) {
        Point.createCartesianPoint(1d, 2d);
        Point.createPolarPoint(2d, 0.03d);
    }
}

enum Type {
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;

    //this isn't a ideal constructor, cause you have hint for user what is a and b corresponding type t
    //if t = CARTESIAN, a is x and b is y, otherwise, a is radius and b is angle. It's so confuse
    public Point(double a, double b, Type t) {
        switch (t) {
            case POLAR:
                this.x = a * Math.cos(b);
                this.y = a * Math.sin(b);
                break;
            case CARTESIAN:
                this.x = a;
                this.y = b;
                break;
            default:
                break;
        }
    }

    //Don't expose constructor
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //create exactly type which expected
    public static Point createCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point createPolarPoint(double r, double a) {
        return new Point(r * Math.cos(a), r * Math.sin(a));
    }

    //We can move factory method into a nested static factory class
    public static class Factory {
        //public static Point createCartesianPoint(double x, double y);
        //public static Point createPolarPoint(double r, double a);
    }
}