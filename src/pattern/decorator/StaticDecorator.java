package pattern.decorator;

import java.util.function.Supplier;

public class StaticDecorator {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        StaticColorShape<Square> colorShape = new StaticColorShape(()->new Square(5), "Red");
        System.out.println(colorShape.info());

        StaticTransparentShape<StaticColorShape> myShape = new StaticTransparentShape(() -> new StaticColorShape(()->new Circle(20), "Green"), 50);
        System.out.println(myShape.info());
    }
}

class StaticColorShape<T extends Shape> implements Shape {
    private Shape shape;//decorate for shape
    private String color;

    public StaticColorShape(Supplier<? extends T> ctor, String color) {
        this.shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class StaticTransparentShape <T extends Shape> implements Shape {
    private Shape shape;//decorate for shape
    private int transparency;

    public StaticTransparentShape(Supplier<? extends T> ctor, int transparency) {
        this.shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";
    }
}