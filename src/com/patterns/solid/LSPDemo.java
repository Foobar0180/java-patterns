package com.patterns.solid;

class Rectangle {
    protected int width, height;

    public Rectangle() {}

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return width * height; }

    @Override
    public String toString() {
        return "com.patterns.solid.Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle {

    public Square() {}

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

class ShapeFactory {
    public static Rectangle createRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle createSquare(int side) {
        return new Rectangle(side, side);
    }
}

class LSPDemo {
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);

        System.out.println("Expected an area of " + (width * 10) +
                ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 3);
        useIt(rectangle);

        Rectangle square = new Square();
        square.setWidth(5);
        useIt(square);
    }
}
