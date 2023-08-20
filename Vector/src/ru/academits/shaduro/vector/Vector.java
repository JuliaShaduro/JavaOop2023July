package ru.academits.shaduro.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int coordinatesCount) {
        if (coordinatesCount <= 0) {
            throw new IllegalArgumentException("Количество координат должна быть больше 0, coordinatesCount = " + coordinatesCount);
        }

        coordinates = new double[coordinatesCount];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double... coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Количество координат = 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int coordinatesCount, double... coordinates) {
        if (coordinatesCount <= 0) {
            throw new IllegalArgumentException("Количество координат должна быть больше 0, coordinatesCount = " + coordinatesCount);
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinatesCount);
    }

    public int getCoordinatesCount() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

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

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : coordinates) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getCoordinate(int index) {
        if (index >= coordinates.length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " за пределами диапазона {0; "
                    + (coordinates.length - 1) + "}");
        }

        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " за пределами диапазона {0; "
                    + (coordinates.length - 1) + "}");
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

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);
        vectorResult.add(vector2);

        return vectorResult;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);
        vectorResult.subtract(vector2);

        return vectorResult;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int coordinatesMinCount = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        double result = 0;

        for (int i = 0; i < coordinatesMinCount; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }
}