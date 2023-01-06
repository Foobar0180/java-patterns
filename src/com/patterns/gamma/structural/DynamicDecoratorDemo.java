package com.patterns.gamma.structural;

import java.util.function.Supplier;

interface GeometricShape {
    String info();
}

class BasicCircle implements GeometricShape {
    private float radius;

    public BasicCircle() {
    }

    public BasicCircle(float radius) {
        this.radius = radius;
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class BasicSquare implements GeometricShape {
    private float side;

    public BasicSquare() {
    }

    public BasicSquare(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square with sides " + side;
    }
}

/* Dynamic Decorators */

class ColoredShape implements GeometricShape {
    private GeometricShape shape;
    private String color;

    public ColoredShape(GeometricShape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape implements GeometricShape {
    private GeometricShape shape;
    private int transparency;

    public TransparentShape(GeometricShape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";
    }
}

/* Static Decorators */

class GenericColoredShape<T extends GeometricShape> implements GeometricShape {
    private GeometricShape shape;
    private String color;

    public GenericColoredShape(Supplier<? extends T> ctor, String color) {
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class GenericTransparentShape<T extends GeometricShape> implements GeometricShape {
    private GeometricShape shape;
    private int transparency;

    public GenericTransparentShape(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";
    }
}

class DynamicDecoratorDemo {
    public static void main(String[] args) {
        // Dynamic demo
        BasicCircle circle = new BasicCircle(10);

        System.out.println(circle.info());


        ColoredShape blueSquare = new ColoredShape(new BasicSquare(20), "blue");
        System.out.println(blueSquare.info());

        final TransparentShape myCircle = new TransparentShape(
                new ColoredShape(
                        new BasicCircle(10), "green"
                ), 50);

        System.out.println(myCircle.info());

        // Static demo
        GenericColoredShape<BasicSquare> yellowSquare = new GenericColoredShape<>(
                () -> new BasicSquare(20),
                "yellow");
        System.out.println(yellowSquare.info());
    }
}
