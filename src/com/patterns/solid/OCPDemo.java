package com.patterns.solid;

import java.util.List;
import java.util.stream.Stream;

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

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE
}

//class com.patterns.solid.ProductFilter {
//    public Stream<com.patterns.solid.Product> filterByProducts(List<com.patterns.solid.Product> products, com.patterns.solid.Color color) {
//        return products.stream().filter(p -> p.color == color);
//    }
//}

class ProductFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items,
                                  Specification<Product> specification) {
        return items.stream().filter(p -> specification.isSatisfied(p));
    }
}

interface Specification<T> {
    boolean isSatisfied(T item);
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class OCPDemo {
    public static void main(String[] args) {
        Product shirt = new Product("Heavy Cotton Tee T-Shirt", Color.GREEN, Size.MEDIUM);
        Product apple = new Product("Pink Pearl Apple", Color.RED, Size.SMALL);
        Product car = new Product("Tesla Model 3", Color.RED, Size.LARGE);

        List<Product> products =  List.of(shirt, apple, car);
        ProductFilter filter = new ProductFilter();

//        // com.patterns.solid.Filter the list of products by color
//        filter.filterByProducts(products, com.patterns.solid.Color.GREEN)
//                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("\nGreen items:");
        filter.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name));

        System.out.println("\nLarge red items:");
        filter.filter(products,
                new AndSpecification<> (
                        new ColorSpecification(Color.RED),
                        new SizeSpecification(Size.LARGE)
                ))
                .forEach(p -> System.out.println(" - " + p.name));
    }
}
