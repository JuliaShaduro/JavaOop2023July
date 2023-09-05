package ru.academits.shaduro.shapes.main;

import ru.academits.shaduro.shapes.*;
import ru.academits.shaduro.shapes.comparators.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(155),
                new Triangle(1, -5, 3, 155, 9, 14),
                new Rectangle(1, 23),
                new Circle(3),
                new Circle(15)
        };

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);
        System.out.println("Фигура с максимальной площадью = " + shapeWithMaxArea);

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes);
        System.out.println("Фигура со 2-ым периметром по величине = " + shapeWithSecondPerimeter);
    }

    private static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пустой.");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пустой.");
        }

        if (shapes.length == 1) {
            throw new IllegalArgumentException("В массиве одна фигура.");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }
}