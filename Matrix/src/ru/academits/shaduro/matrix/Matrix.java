
package ru.academits.shaduro.matrix;

import ru.academits.shaduro.vector.Vector;

import java.util.Arrays;

import static java.util.Arrays.copyOf;

//Todo что лучше сделать копию объекта или через методы вызывать?
public class Matrix {
    private Vector[] rows;
    private final int rowCount;  // Todo надо отдельно выносить переменный? Часто используются в методах.
    // Todo код падает на rows[0].getCoordinatesCount() если rowCount = 0 или через метод вызываем параметры ? Удобно сразу определить при созд объекта.
    private final int columnCount;

    public Matrix (int rowCount, int columnCount) {
        rows = new Vector[rowCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnCount);
        }

        this.rowCount = rows.length;
        this.columnCount = rows[0].getCoordinatesCount();
    }

    public Matrix (Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        System.arraycopy(matrix.rows, 0, this.rows, 0, rows.length);

        this.columnCount = rows[0].getCoordinatesCount();
        this.rowCount = rows.length;
    }

    public Matrix (double[][] coordinates) {
        rows = new Vector[coordinates.length];
        int rowMaxLength = 0;

        for (int i = 0; i < coordinates.length - 1; i++) {
            rowMaxLength = Math.max(rowMaxLength, coordinates[i].length);
        }

        for (int i = 0; i < coordinates.length; i++) {
            rows[i] = new Vector(copyOf(coordinates[i], rowMaxLength));
        }

        this.columnCount = rows[0].getCoordinatesCount();
        this.rowCount = rows.length;
    }

    public Matrix (Vector[] vectors) {
        rows = new Vector[vectors.length];
        int rowMaxLength = 0;

        for (Vector vector : vectors) {
            rowMaxLength = Math.max(rowMaxLength, vector.getCoordinatesCount());
        }

        double[] coordinates = new double[rowMaxLength];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getCoordinatesCount(); j++) {
                coordinates[j] = vectors[i].getCoordinate(j);
            }

            rows[i] = new Vector(rowMaxLength, coordinates);
        }

        this.columnCount = rows[0].getCoordinatesCount();
        this.rowCount = rows.length;
    }

    public int getColumnCount () {
        if (rowCount == 0) {
            throw new IllegalArgumentException("Количество строк должна быть больше 0, ColumnCount = " + rows.length);
        }

        return rowCount;
    }

    public int getRowCount () {
        if (columnCount > 0) {
            return columnCount;
        }

        throw new IllegalArgumentException("Количество столбцов должна быть больше 0, ColumnCount = " + rows[0].getCoordinatesCount());
    }

    public Vector getVector (int index) {
        if (index >= rowCount || index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " за пределами диапазона {0; " + (rowCount - 1) + "}");
        }

        return rows[index];
    }

    public Vector setVector (Vector vector, int index) {
        if (index >= rowCount || index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " за пределами диапазона {0; " + (rowCount - 1) + "}");
        }

        if (vector.getCoordinatesCount() == 0) {
            throw new IllegalArgumentException("Количество координат в векторе должна быть больше 0. Вектор пустой.");
        }

        return rows[index] = new Vector(vector);
    }

    public Vector getColumnInMatrix (int index) {//Todo переделать
        if (index >= rowCount || index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " за пределами диапазона {0; " + (rowCount - 1) + "}");
        }

        Vector resultVector = new Vector(rowCount);

        for (int i = 0; i < rowCount; i++) {
            resultVector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return resultVector;
    }

    public void transposition () {
        if (rowCount < 0) {
            throw new IllegalArgumentException("Количество строк должна быть больше 0, rowCount = " + rowCount);
        }
        Vector[] columns = new Vector[columnCount];

        for (int i = 0; i < columnCount; i++) {
            columns[i] = getColumnInMatrix(i);
        }

        rows = columns;
    }

    public void multiplyByScalar (double number) {
        if (rowCount == 0) {
            throw new IllegalArgumentException("Длина матрицы должна быть > 0. Rows.length = " + rowCount);
        }

        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public double determinant () {
        if (rowCount == 0) {
            throw new IllegalArgumentException("Длина матрицы должна быть > 0. Rows.length = " + rowCount);
        }

        //Todo может сделать копию double [][] и через него обращаться к данным??
        Matrix matrix = new Matrix(rows);

        double result = 1;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 1 + i; j < rowCount; j++) {
                double supportElement = matrix.rows[j].getCoordinate(i) / matrix.rows[i].getCoordinate(i);

                for (int k = 0; k < rowCount; k++) {
                    matrix.rows[j].setCoordinate(k, matrix.rows[j].getCoordinate(k) - (supportElement * matrix.rows[i].getCoordinate(k)));
                }
            }

            result *= matrix.rows[i].getCoordinate(i);
        }

        return result;
    }

    @Override
    public String toString () {

        StringBuilder sb = new StringBuilder("{");

        sb.append(Arrays.toString(rows)).append(", ").delete(1, 2).delete(sb.length() - 3, sb.length()).append('}');

        return sb.toString();
    }

    public Vector multiplyingByVector (Vector vector) {
        if (vector.getCoordinatesCount() != columnCount) {
            throw new IllegalArgumentException("Количество строк в векторе должно совпадать с количеством столбцов матрице. "
                    + "Длина вектора = " + vector.getCoordinatesCount());
        }

//        for (int i = 0; i < columnCount; i++) {
//           this.setVector(rows[i], i).multiplyByScalar(vector.getCoordinate(i));
        //       ????
//        }
        Vector resultVector = new Vector(columnCount);
        double result = 0;

        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                result += rows[i].getCoordinate(j) * vector.getCoordinate(j);
            }

            resultVector.setCoordinate(i, result);
            result = 0;
        }

        return resultVector;
    }

    public void add (Matrix matrix) {
        if (matrix.rowCount != rowCount) {
            throw new IllegalArgumentException("Матрицы разной размерности складывать нельзя. Длина матрицы должна быть = " + rowCount);
        }

        for (int i = 0; i < rowCount; i++) {
            rows[i].add(matrix.getVector(i));
        }
    }

    public void subtract (Matrix matrix) {
        if (matrix.rowCount != rowCount) {
            throw new IllegalArgumentException("Матрицы разной размерности вычитать нельзя. Длина матрицы должна быть = " + rowCount);
        }

        for (int i = 0; i < rowCount; i++) {
            rows[i].subtract(matrix.getVector(i));
        }
    }

    public static Matrix getSum (Matrix matrix1, Matrix matrix2) {  // Todo проверить везде фильтры исключений
        if (matrix1.rowCount != matrix2.rowCount) {
            throw new IllegalArgumentException("Матрицы разной размерности складывать нельзя. Длина матрицы1 = "
                    + matrix1.rowCount + ". Длина матрицы2 = " + matrix2.rowCount);
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference (Matrix matrix1, Matrix matrix2) {
        if (matrix1.rowCount != matrix2.rowCount) {
            throw new IllegalArgumentException("Матрицы разной размерности вычитать нельзя. Длина матрицы1 = "
                    + matrix1.rowCount + " Длина матрицы2 = " + matrix2.rowCount);
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getMultiplication (Matrix matrix1, Matrix matrix2) {
        if (matrix1.columnCount != matrix2.rowCount) {
            throw new IllegalArgumentException("Количество столбцов в первой матрице неравно количеству строк во второй матрице. Количество столбцов в первой матрице = "
                    + matrix1.columnCount + ". Количеству строк во второй матрице = " + matrix2.rowCount);
        }

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < matrix1.rowCount; i++) {
            resultMatrix.rows[i] = matrix1.multiplyingByVector(matrix2.getColumnInMatrix(i));
        }

        resultMatrix.transposition();

        return resultMatrix;
    }
}