package com.patterns.gamma.structural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name = "Group";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    private void print(StringBuilder builder, int depth) {
        builder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());

        for (GraphicObject child : children) {
            child.print(builder, depth + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(builder, 0);
        return builder.toString();
    }
}

class Triangle extends GraphicObject {
    public Triangle(String color) {
        name = "Triangle";
        this.color = color;
    }
}

class Square extends GraphicObject {
    public Square(String color) {
        name = "Square";
        this.color = color;
    }
}

/*
* A mechanism for treating individual and aggregate objects in a uniform manner, regardless of inheritance or
* composition. Composition allows us to make compound objects i.e. objects composed of other objects.
* */
class CompositeDemo {

    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Triangle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Square("Blue"));
        group.children.add(new Triangle("Blue"));

        drawing.children.add(group);

        System.out.println(drawing);
    }
}
