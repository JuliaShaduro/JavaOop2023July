package ru.academits.shaduro.vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int coordinatesCount) {
        if (coordinatesCount <= 0) {
            throw new IllegalArgumentException("Количество координат <= 0, coordinatesCount = " + coordinatesCount);
        }

        coordinates = new double[coordinatesCount];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates,
                vector.coordinates.length);
    }

    public Vector(double... coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Количество координат = 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int coordinatesCount, double... coordinates) {
        if (coordinatesCount <= 0) {
            throw new IllegalArgumentException("Количество координат <= 0, coordinatesCount = " + coordinatesCount);
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinatesCount);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double coordinate : coordinates) {
            stringBuilder.append(coordinate).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }
    }

    public void unwrap() {
        multiplyByScalar(-1);
    }

    public double getSum() {
        double length = 0;

        for (double e : coordinates) {
            length += e * e;
        }

        return Math.sqrt(length);
    }

    public double getCoordinate(int index) {
        if (index >= coordinates.length || index < 0) {
            throw new IllegalArgumentException("Индекс за пределами массива, index = " + index);
        }

        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        if (index < 0 || index >= coordinates.length) {
            throw new IllegalArgumentException("Индекс за пределами массива, index = " + index);
        }

        coordinates[index] = coordinate;
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

        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(coordinates);
        return hash;
    }

    public static Vector getResultSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);

        return vector;
    }

    public static Vector getResultDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtract(vector2);

        return vector;
    }

    public static double getProductScalar(Vector vector1, Vector vector2) {
        if (vector1.coordinates.length < vector2.coordinates.length) {
            vector1.coordinates = Arrays.copyOf(vector1.coordinates, vector2.coordinates.length);
        }

        double scalar = 0;

        for (int i = 0; i < vector1.coordinates.length; i++) {
            scalar += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return scalar;
    }
}