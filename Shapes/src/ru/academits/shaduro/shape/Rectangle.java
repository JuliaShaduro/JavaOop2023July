package ru.academits.shaduro.shape;

public class Rectangle implements Shape {
    private double widthSide;
    private double heightSide;

    public Rectangle(double widthSide, double heightSide) {
        this.widthSide = widthSide;
        this.heightSide = heightSide;
    }

    public double getWidthSide() {
        return widthSide;
    }

    public void setWidthSide(double widthSide) {
        this.widthSide = widthSide;
    }

    public double getHeightSide() {
        return heightSide;
    }

    public void setHeightSide(double heightSide) {
        this.heightSide = heightSide;
    }

    @Override
    public double getWidth() {
        return widthSide;
    }

    @Override
    public double getHeight() {
        return heightSide;
    }

    @Override
    public double getArea() {
        return widthSide * heightSide;
    }

    @Override
    public double getPerimeter() {
        return 2 * (widthSide + heightSide);
    }

    @Override
    public String toString() {
        return "Rectangle = { widthSide = " + widthSide + ", heightSide = " + heightSide + ", Area = " + getArea() + ", Perimeter = " + getPerimeter() + '}';
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
        return widthSide == rectangle.widthSide && heightSide == rectangle.heightSide;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(heightSide) + Double.hashCode(widthSide);
    }
}