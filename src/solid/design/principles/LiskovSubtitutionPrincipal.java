package solid.design.principles;

/**
 * objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program
 * (Các instance của lớp con có thể thay thế được instance lớp cha mà vẫn đảm bảo tính đúng đắn của chương trình)
 */
public class LiskovSubtitutionPrincipal {
    static void useIt(Rectangle rt) {
        int width = rt.getWidth();
        rt.setheight(10);
        System.out.println("Expected area of " + 10 * width + ", got " + rt.area());
    }

    public static void main(String[] args) {
        //Rectagle is OK
        Rectangle rt = new Rectangle(2, 3);
        useIt(rt);

        //Square is not OK
        Rectangle sq = new Square(5);
        useIt(sq);
    }
}

class Rectangle {
    protected int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getheight() {
        return height;
    }

    public void setheight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }

    public String toString() {
        return "Rectangle {" +
                "width: " + width +
                ",height: " + height +
                "}";
    }

    //Resolve using void
    public boolean isSquare() {
        return height == width;
    }
}

//Resolve using factory
class RectangleFactory {
    public static Rectangle newRectagle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int size) {
        return new Rectangle(size, size);
    }
}

//violate Liskov Principal
class Square extends Rectangle {
    public Square() {

    }

    public Square(int size) {
        super(size, size);
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setheight(int height) {
        this.width = height;
        this.height = height;
    }
}