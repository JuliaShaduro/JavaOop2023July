package ru.academits.shaduro.matrix;

import ru.academits.shaduro.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount == 0 || columnsCount == 0) {
            throw new IllegalArgumentException("Кол-во строк и столбцов должно быть больше 0. Кол-во строк = " + rowsCount
                    + ". Кол-во столбцов = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] coordinates) {
        if (coordinates.length == 0 || coordinates[0].length == 0) {
            throw new IllegalArgumentException("Кол-во строк и столбцов должно быть больше 0. Кол-во строк = " + coordinates.length
                    + ". Кол-во столбцов = 0");
        }

        rows = new Vector[coordinates.length];
        int rowMaxLength = 0;

        for (double[] coordinate : coordinates) {
            rowMaxLength = Math.max(rowMaxLength, coordinate.length);
        }

        for (int i = 0; i < coordinates.length; i++) {
            rows[i] = new Vector(rowMaxLength, coordinates[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0 || vectors[0].getLength() == 0) {
            throw new IllegalArgumentException("Кол-во строк и столбцов должно быть больше 0. Кол-во строк = " + vectors.length
                    + ". Кол-во столбцов = 0");
        }

        rows = new Vector[vectors.length];
        int rowMaxLength = 0;

        for (Vector vector : vectors) {
            rowMaxLength = Math.max(rowMaxLength, vector.getCoordinatesCount());
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(rowMaxLength);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() { //
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getCoordinatesCount();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс строки = " + index + " за пределами диапазона {0; " + (rows.length - 1) + "}");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс строки = " + index + " за пределами диапазона {0; " + (rows.length - 1) + "}");
        }

        if (vector.getCoordinatesCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Разная длина строк. Длина передаваемой строки = " + vector.getCoordinatesCount()
                    + ". Длина строки в матрице = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс столбца = " + index + " за пределами диапазона {0; " + (getColumnsCount() - 1) + "}");
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return resultVector;
    }

    public void transpose() {
        int newLight = getColumnsCount();
        Vector[] columns = new Vector[newLight];

        for (int i = 0; i < newLight; i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    public void multiplyByScalar(double number) {
        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Определитель можно вычислить только для квадратной матрицы. Кол-во строк в матрице = "
                    + rows.length + ". Кол-во столбцов в матрице = " + getColumnsCount());
        }

        Matrix matrix = new Matrix(rows);

        double determinant = 1;
        double epsilon = 1.0e-10;

        for (int i = 0; i < rows.length; i++) {
            if (Math.abs(matrix.rows[i].getCoordinate(i)) <= epsilon) {
                int j = i + 1;

                while (j < rows.length) {
                    if (Math.abs(matrix.rows[j].getCoordinate(i)) > epsilon) {
                        matrix.rows[i].reverse();

                        Vector vectorTemp = matrix.getRow(j);
                        matrix.setRow(j, matrix.rows[i]);
                        matrix.setRow(i, vectorTemp);
                        break;
                    }

                    j++;
                }
            }

            for (int j = 1 + i; j < rows.length; j++) {
                if (Math.abs(matrix.rows[j].getCoordinate(i)) <= epsilon) {
                    continue;
                }

                double supportElement = matrix.rows[j].getCoordinate(i) / matrix.rows[i].getCoordinate(i);

                for (int k = 0; k < rows.length; k++) {
                    double number = matrix.rows[j].getCoordinate(k) - (supportElement * matrix.rows[i].getCoordinate(k));
                    matrix.rows[j].setCoordinate(k, number);
                }
            }

            determinant *= matrix.rows[i].getCoordinate(i);

            if (determinant == 0) {
                return 0;
            }
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Vector vector : rows) {
            sb.append(vector).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()).append('}');

        return sb.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getCoordinatesCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество элементов в векторе должно совпадать с количеством столбцов матрице." +
                    "Количество столбцов в матрице = " + getColumnsCount()
                    + ". Количество элементов в векторе = " + vector.getCoordinatesCount());
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setCoordinate(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        Matrix.validationEqualityOfSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        Matrix.validationEqualityOfSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix.validationEqualityOfSizes(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix.validationEqualityOfSizes(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Кол-во столбцов в 1-ой матрице неравно количеству строк во второй матрице. Кол-во столбцов в 1-ой матрице = "
                    + matrix1.getColumnsCount() + ". Кол-ву строк во 2-ой матрице = " + matrix2.rows.length);
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                resultMatrix.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }

    private static void validationEqualityOfSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Складывать/вычитать можно только одинаковые по размеру матрицы. Кол-во строк в 1-ой матрице = "
                    + matrix1.rows.length + ". Во 2-ой = " + matrix2.rows.length + ". Кол-во столбцов в 1-ой матрице = " + matrix1.getColumnsCount()
                    + ". Во 2-ой = " + matrix2.getColumnsCount());
        }
    }
}