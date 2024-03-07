package ru.academits.shaduro.matrix;

import ru.academits.shaduro.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
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

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Кол-во строк должно быть больше 0. Кол-во строк = " + array.length);
        }

        rows = new Vector[array.length];

        int rowMaxLength = 0;

        for (double[] row : array) {
            rowMaxLength = Math.max(rowMaxLength, row.length);
        }

        if (rowMaxLength == 0) {
            throw new IllegalArgumentException("Кол-во столбцов должно быть больше 0. Кол-во столбцов = " + rowMaxLength);
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(rowMaxLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер массива векторов не может быть равен 0.");
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

    public int getRowsCount() {
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
        Vector[] columns = new Vector[getColumnsCount()];

        for (int i = 0; i < columns.length; i++) {
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
        final double epsilon = 1.0e-10;

        for (int i = 0; i < rows.length; i++) {
            if (Math.abs(matrix.rows[i].getCoordinate(i)) <= epsilon) {
                for (int j = i + 1; j < rows.length; j++) {
                    if (Math.abs(matrix.rows[j].getCoordinate(i)) > epsilon) {
                        matrix.rows[i].reverse();

                        Vector tempRow = matrix.rows[j];
                        matrix.rows[j] = matrix.rows[i];
                        matrix.rows[i] = tempRow;

                        break;
                    }
                }
            }

            for (int j = i + 1; j < rows.length; j++) {
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

            if (Math.abs(determinant) <= epsilon) {
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
            throw new IllegalArgumentException("Количество элементов в векторе должно совпадать с количеством столбцов в матрице. "
                    + "Количество столбцов в матрице = " + getColumnsCount()
                    + ". Количество элементов в векторе = " + vector.getCoordinatesCount());
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setCoordinate(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkMatricesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Кол-во столбцов в 1-ой матрице неравно количеству строк во второй матрице."
                    + " Кол-во столбцов в 1-ой матрице = "
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

    private static void checkMatricesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Складывать/вычитать можно только одинаковые по размеру матрицы. Кол-во строк в 1-ой матрице = "
                    + matrix1.rows.length + ". Во 2-ой = " + matrix2.rows.length + ". Кол-во столбцов в 1-ой матрице = " + matrix1.getColumnsCount()
                    + ". Во 2-ой = " + matrix2.getColumnsCount());
        }
    }
}