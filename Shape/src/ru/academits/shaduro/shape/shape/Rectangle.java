package ru.academits.shaduro.shape.shape;

import ru.academits.shaduro.shape.Shape;

public class Rectangle implements Shape {
    private final double aSide;
    private final double bSide;

    public Rectangle(double aSide, double bSide) {
        this.aSide = aSide;
        this.bSide = bSide;
    }

    @Override
    public double getWidth() {
        return aSide;
    }

    @Override
    public double getHeight() {
        return bSide;
    }

    @Override
    public double getArea() {
        return aSide * bSide;
    }

    @Override
    public double getPerimeter() {
        return 2 * (aSide + bSide);
    }

    @Override
    public String toString() {
        return "Rectangle = {" + "aSide = " + aSide + ", bSide = " + bSide + ", Area = " + getArea() + ", Perimeter = " + getPerimeter() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;
        return aSide == rectangle.aSide && bSide == rectangle.bSide;
    }

    @Override

    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(bSide) + Double.hashCode(aSide);
    }
}