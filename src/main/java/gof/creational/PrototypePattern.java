package gof.creational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class PrototypePattern {
    public static void main(String[] args) {
        List<Shape> listOfShapes = new ArrayList<>();
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();
        rectangle.setX(2);
        rectangle.setY(1);
        ((Rectangle) rectangle).setHeight(10);
        ((Rectangle) rectangle).setWidth(20);
        circle.setColor("Red");
        circle.setX(2);
        circle.setY(4);
        listOfShapes.add(circle);
        listOfShapes.add(rectangle);
        listOfShapes.stream().forEach(x-> System.out.println(x));

        List<Shape> shapesCopy = listOfShapes.stream().peek(x->x.clone()).collect(Collectors.toList());

        log.info("Copied array " + shapesCopy);

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
abstract class Shape{
    int x;
    int y;
    String color;

    protected abstract Shape clone();

    Shape(Shape shape){
        this();
        this.x = shape.x;
        this.y = shape.y;
        this.color = shape.color;
    }
}

@NoArgsConstructor
@Data
class Rectangle extends Shape {
    private int width;
    private int height;

    Rectangle(Rectangle rectangle){
        super(rectangle);
        this.height = rectangle.height;
        this.width = rectangle.width;
    }
    @Override
    protected Shape clone() {
        return new Rectangle(this);
    }
}

@NoArgsConstructor
@Data
class Circle extends Shape {
    private int radius;

    Circle(Circle circle){
        super(circle);
        this.radius = circle.radius;
    }

    @Override
    protected Shape clone() {
        return new Circle(this);
    }
}

