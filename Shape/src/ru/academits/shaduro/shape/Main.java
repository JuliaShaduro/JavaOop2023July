package ru.academits.shaduro.shape;

import ru.academits.shaduro.shape.shape.Circle;
import ru.academits.shaduro.shape.shape.Rectangle;
import ru.academits.shaduro.shape.shape.Square;
import ru.academits.shaduro.shape.shape.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape square1 = new Square(155);

        System.out.println("Высота квадрата = " + square1.getHeight());
        System.out.println("Ширина квадрата = " + square1.getWidth());
        System.out.println("Площадь квадрата = " + square1.getArea());
        System.out.println("Периметр квадрата = " + square1.getPerimeter());
        System.out.println(square1);
        System.out.println("Hash = " + square1.hashCode());

        Shape square2 = new Square(15);
        System.out.println("Равенство объектов через equals = " + square1.equals(square2));
        System.out.println("______________________");

        Shape triangle1 = new Triangle(1, -5, 3, 8, 9, 14);

        System.out.println("Высота треугольника = " + triangle1.getHeight());
        System.out.println("Ширина треугольника = " + triangle1.getWidth());
        System.out.println("Площадь треугольника = " + triangle1.getArea());
        System.out.println("Периметр треугольника = " + triangle1.getPerimeter());
        System.out.println(triangle1);
        System.out.println("Hash = " + triangle1.hashCode());

        Shape triangle2 = new Triangle(1, 5, 3, -8, 9, 14);
        System.out.println("Равенство объектов через equals = " + triangle1.equals(triangle2));
        System.out.println("______________________");

        Shape rectangle1 = new Rectangle(1, 15);

        System.out.println("Высота прямоугольника = " + rectangle1.getHeight());
        System.out.println("Ширина прямоугольника = " + rectangle1.getWidth());
        System.out.println("Площадь прямоугольника = " + rectangle1.getArea());
        System.out.println("Периметр прямоугольника = " + rectangle1.getPerimeter());
        System.out.println(rectangle1);
        System.out.println("Hash = " + rectangle1.hashCode());

        Shape rectangle2 = new Rectangle(1, 15);

        System.out.println("Равенство объектов через equals = " + rectangle1.equals(rectangle2));
        System.out.println("______________________");

        Shape circle1 = new Circle(3);

        System.out.println("Высота окружности = " + circle1.getHeight());
        System.out.println("Ширина окружности = " + circle1.getWidth());
        System.out.println("Площадь окружности = " + circle1.getArea());
        System.out.println("Периметр окружности = " + circle1.getPerimeter());
        System.out.println(circle1);
        System.out.println("Hash = " + circle1.hashCode());

        Shape circle2 = new Circle(3);

        System.out.println("Равенство объектов через equals = " + circle1.equals(circle2));
        System.out.println("______________________");

        Shape maxShape = getShapeWithMaxArea(square1, triangle1, rectangle1, circle1, circle2);

        System.out.println("Площадь максимальной фигуры = " + maxShape.getArea());
        System.out.println(maxShape);

        Shape secondPerimeter = getShapeWithSecondPerimeter(square1, triangle1, rectangle1, circle1, circle2);

        System.out.println("Периметр 2-ой фигуры = " + secondPerimeter.getPerimeter());
        System.out.println(secondPerimeter);
    }

    private static Shape getShapeWithMaxArea(Shape... shapes) {
        Arrays.sort(shapes, new ShapeComparatorArea());
        return shapes[0];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        Arrays.sort(shapes, new ShapeComparatorPerimeter());
        return shapes[1];
    }
}