package ru.academits.shaduro.shapes.main;

import ru.academits.shaduro.shapes.*;
import ru.academits.shaduro.shapes.comparators.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape circle1 = new Circle(3);

        System.out.println(circle1);
        System.out.println("Hash = " + circle1.hashCode());

        Circle circle2 = new Circle(15);
        System.out.println(circle2);
        System.out.println(circle2.getRadius());
        circle2.setRadius(23);
        System.out.println(circle2);

        System.out.println("Равенство объектов через equals = " + circle1.equals(circle2));
        System.out.println("______________________");

        Shape square1 = new Square(155);

        System.out.println(square1);
        System.out.println("Hash = " + square1.hashCode());

        Shape square2 = new Square(15);

        System.out.println("Равенство объектов через equals = " + square1.equals(square2));
        System.out.println("______________________");

        Shape triangle1 = new Triangle(1, -5, 3, 155, 9, 14);

        System.out.println(triangle1);
        System.out.println("Hash = " + triangle1.hashCode());

        Shape triangle2 = new Triangle(1, 5, 3, -8, 9, 14);
        System.out.println("Равенство объектов через equals = " + triangle1.equals(triangle2));
        System.out.println("______________________");

        Shape rectangle1 = new Rectangle(1, 23);

        System.out.println(rectangle1);
        System.out.println("Hash = " + rectangle1.hashCode());

        Shape rectangle2 = new Rectangle(1, 15);

        System.out.println("Равенство объектов через equals = " + rectangle1.equals(rectangle2));
        System.out.println("______________________");

        Shape[] shapes1 = {square1, triangle1, rectangle1, circle1, circle2};

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes1);

        System.out.println("Фигура с максимальной площадью = " + new StringBuilder(shapeWithMaxArea.toString()).
                delete(shapeWithMaxArea.toString().indexOf(" ="), shapeWithMaxArea.toString().length()));
//        Если так, то надо делать переменную StringBuilder.
        //  new StringBuilder - потенциальный объект на удаление сборщиком ,если без переменной?
//
//        StringBuilder st = new StringBuilder(shapeWithMaxArea.toString());
//        st.delete(st.indexOf(" ="), st.length());
//        System.out.println(st);

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes1);

        System.out.println("Фигура со 2-ым периметром по величине = " + new StringBuilder(shapeWithSecondPerimeter.toString()).
                delete(shapeWithSecondPerimeter.toString().indexOf(" ="), shapeWithSecondPerimeter.toString().length()));
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