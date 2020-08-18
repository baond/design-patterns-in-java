package solid.design.principles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * A class must be open to extend and close to modify
 */
public class OpenClosePrinciple {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.HUGE);

        List<Product> products = Arrays.asList(apple, tree, house);
        ProductFilter pf = new ProductFilter();
        System.out.println("Green Product (by old method): ");
        pf.filterByColor(products, Color.GREEN).forEach(p-> System.out.println(" - " + p.name + " is green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green Product (by better method): ");
        ColorSpecification colorSpecification = new ColorSpecification(Color.GREEN);
        bf.filter(products, colorSpecification).forEach(p-> System.out.println(" - " + p.name + " is green"));
    }
}

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color.equals(color));
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size.equals(size));
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
        return products.stream().filter(p -> p.size.equals(size) && p.color.equals(color));
    }
}

interface Specification<T> {
    boolean isSatified(T item);
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatified(T item) {
        return first.isSatified(item) && second.isSatified(item);
    }
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatified(Product item) {
        return item.color.equals(color);
    }
}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(item -> spec.isSatified(item));
    }
}