package pattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Composite pattern is mechanism for treating individual object and composition of object in a uniform manner
 */
public class GeometricShape {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.childrens.add(new Square("Red"));
        drawing.childrens.add(new Circle("Blue"));
        GraphicObject group = new GraphicObject();
        group.childrens.add(new Circle("Green"));
        group.childrens.add(new Square("Yellow"));
        drawing.childrens.add(group);
        System.out.println(drawing.toString());
    }
}

class GraphicObject {
    protected String name = "Group";
    public String color;
    public List<GraphicObject> childrens = new ArrayList<>();

    public GraphicObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append(color == null || color.isEmpty() ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : childrens) {
            child.print(sb, depth + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        name = "Circle";
        this.color = color;
    }
}

class Square extends GraphicObject {
    public Square(String color) {
        name = "Square";
        this.color = color;
    }
}