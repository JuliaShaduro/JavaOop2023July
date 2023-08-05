package ru.academits.shaduro.vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество координат <= 0 ");
        }

        array = new double[n];
    }

    public void setArray(double[] array) {
        this.array = array;
    }

    public Vector(Vector vector) {
        this.array = vector.array;
    }

    public Vector(double... array) {
        this.array = array;
    }

    public Vector(int n, double... array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество координат <= 0 ");
        }

        this.array = Arrays.copyOf(array, n);
    }

    public int getSize() {
        return array.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]);

            if (i + 1 < array.length) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("}");

        return String.valueOf(stringBuilder);
    }

    public void getVectorsSum(Vector vector) {
        if (getSize() < vector.getSize()) {
            this.array = Arrays.copyOf(array, vector.getSize());
        }

        for (int i = 0; i < array.length; i++) {
            array[i] += i < vector.array.length ? vector.array[i] : 0;
        }
    }

    public void getVectorsDifference(Vector vector) {
        if (getSize() < vector.getSize()) {
            this.array = Arrays.copyOf(array, vector.getSize());
        }

        for (int i = 0; i < array.length; i++) {
            array[i] -= i < vector.array.length ? vector.array[i] : 0;
        }
    }

    public double[] getScalarProduct(double number) {
        double[] scalarProduct = new double[getSize()];

        for (int i = 0; i < scalarProduct.length; i++) {
            scalarProduct[i] = array[i] * number;
        }

        return scalarProduct;
    }

    public double[] getVectorTurnaround() {
        return getScalarProduct(-1);
    }

    public double getVectorLength() {
        int length = 0;

        for (double e : array) {
            length += e * e;
        }

        return Math.sqrt(length);
    }

    public double getIndex(int index) {
        if (index >= getSize() || index < 0) {
            return -1;
        }

        return array[index];
    }

    public double[] setCoordinates(int index, double coordinate) {
        if (index >= getSize() || index < 0) {
            return null;
        }

        array[index] = coordinate;

        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(array, vector.array) && (getSize() == vector.getSize());
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + getSize();
        hash = prime * hash + Arrays.hashCode(array);
        return hash;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.getVectorsSum(vector2);

        return vector;
    }

    public static Vector getVectorsDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.getVectorsDifference(vector2);

        return vector;
    }

    public static double getVectorsScalarProduct(Vector vector1, Vector vector2) {
        double[] vectorsScalarProduct = new double[Math.max(vector1.getSize(), vector2.getSize())];
        double scalar = 0;

        for (int i = 0; i < vectorsScalarProduct.length; i++) {
            scalar += (i < vector1.array.length ? vector1.array[i] : 0) * (i < vector2.array.length ? vector2.array[i] : 0);
        }

        return scalar;
    }
}