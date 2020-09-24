package pattern.decorator;

/**
 * Facilitates the addition of behaviors to individual objects without inheriting from them
 */
public class DynamicDecorator {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColorShape colorShape = new ColorShape(new Square(5), "Red");
        System.out.println(colorShape.info());

        TransparentShape myShape = new TransparentShape(colorShape, 50);
        System.out.println(myShape.info());
    }
}

interface Shape {
    public String info();
}

class Circle implements Shape {

    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public void resize(float ratio){
        radius *= ratio;
    }

    @Override
    public String info() {
        return "A Circle with radius " + radius;
    }
}

class Square implements Shape {

    private float size;

    public Square(float size) {
        this.size = size;
    }

    public void resize(float ratio){
        size *= ratio;
    }

    @Override
    public String info() {
        return "A Square with size " + size;
    }
}

class ColorShape implements Shape {
    private Shape shape;//decorate for shape
    private String color;

    public ColorShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape implements Shape {
    private Shape shape;//decorate for shape
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";
    }
}