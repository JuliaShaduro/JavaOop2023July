package ru.academits.shaduro.shape.shape;

import ru.academits.shaduro.shape.Shape;

import java.util.Objects;

public class Triangle implements Shape {
    private final double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double tempMax = Math.max(x1, x2);
        double tempMin = Math.max(x1, x2);
        return Math.max(tempMax, x3) - Math.min(tempMin, x3);
    }

    @Override
    public double getHeight() {
        double tempMax = Math.max(y1, y2);
        double tempMin = Math.max(y1, y2);
        return Math.max(tempMax, y3) - Math.min(tempMin, y3);
    }

    @Override
    public double getArea() {
        return (Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2);
    }

    @Override
    public double getPerimeter() {
        double aSide = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double bSide = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double cSide = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        return aSide + bSide + cSide;
    }

    @Override
    public String toString() {
        return "Triangle = {" +
                "x1 = " + x1 +
                ", y1 = " + y1 +
                ", x2 = " + x2 +
                ", y2 = " + y2 +
                ", x3 = " + x3 +
                ", y3 = " + y3 + ", Area = " + getArea() + ", Perimeter = " + getPerimeter() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3 &&
                y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}