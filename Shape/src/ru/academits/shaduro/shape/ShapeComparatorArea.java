package ru.academits.shaduro.shape;

import java.util.Comparator;

public class ShapeComparatorArea implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}