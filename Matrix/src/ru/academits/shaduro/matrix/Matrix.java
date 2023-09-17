
package ru.academits.shaduro.matrix;

import ru.academits.shaduro.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowCount, int columnCount) {
        rows = new Vector[rowCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] coordinates) {
        rows = new Vector[coordinates.length];
        int rowMaxLength = 0;

        for (int i = 0; i < coordinates.length - 1; i++) {
            rowMaxLength = Math.max(rowMaxLength, coordinates[i].length);
        }

        for (int i = 0; i < coordinates.length; i++) {
            rows[i] = new Vector(rowMaxLength, coordinates[i]);
        }
    }

    public Matrix(Vector[] vectors) {
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

            rows[i] = new Vector(coordinates);
        }
    }

    /*4.
    - эти геттеры не должны бросать исключение, т.к. конструкторы не должны позволять создать матрицу размера 0
    //Todo конструкторы вектора должен не позволять это делать?
    */
    public int getRowsCount() { //
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getCoordinatesCount();
    }

    /*14.
15. getVector - метод не должен выдавать ссылку на вектор из массива векторов.
Иначе через выданный вектор смогут поменять матрицу. //Todo как можно поменять при  return rows[index];? Можно пример ?:
//Todo вызвать через цикл прокрутить и умножить на рандом число    ?

Нужно выдавать копию
*/

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
                    + "Длина строки в матрице = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= rows[0].getCoordinatesCount()) {
            throw new IndexOutOfBoundsException("Индекс столбца = " + index + " за пределами диапазона {0; " + (rows.length - 1) + "}");
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return resultVector;
    }

    public void transpose() {
        Vector[] columns = new Vector[getRowsCount()];

        for (int i = 0; i < getRowsCount(); i++) {
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
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Определитель можно вычислить только для квадратной матрицы. Кол-во строк в матрице = "
                    + getRowsCount() + "Кол-во столбцов в матрице = " + getColumnsCount());
        }
        //Todo может сделать копию double [][]? и через него обращаться к данным?Это оптимальней чем делать объект matrix ".
        //Todo если оставить как есть, то кажется, что через копию объекта происходит дольше.
        // Т.к надо постоянно обращаться к методам чтобы получить данные
//        double[][] array = new double[getRowsCount()][];
//
//        for (int i = 0; i < getRowsCount(); i++) {
//            for (int j = 0; j < getRowsCount(); j++) {
//                array[i][j] = rows[i].getCoordinate(i);
//            }
//        }

        Matrix matrix = new Matrix(rows);

        double result = 1;
        double epsilon = 1.0e-10;

        for (int i = 0; i < getRowsCount(); i++) {
            if (Math.abs(matrix.rows[i].getCoordinate(i)) <= epsilon) {
                int j = i + 1;

                while (j < getRowsCount()) {
                    if (matrix.rows[j].getCoordinate(i) != 0) {
                        matrix.rows[i].reverse();

                        Vector vectorTemp = matrix.getRow(j);
                        matrix.setRow(j, matrix.rows[i]);
                        matrix.setRow(i, vectorTemp);
                        break;
                    }

                    j++;
                }
            }
            for (int j = 1 + i; j < getRowsCount(); j++) {
                if (matrix.rows[j].getCoordinate(i) == 0) {
                    continue;
                }

                double supportElement = matrix.rows[j].getCoordinate(i) / matrix.rows[i].getCoordinate(i); // Todo после деления нужно проверять на epsilon?

                for (int k = 0; k < getRowsCount(); k++) {
                    double number = matrix.rows[j].getCoordinate(k) - (supportElement * matrix.rows[i].getCoordinate(k));
                    matrix.rows[j].setCoordinate(k, number);
                }
            }

            result *= matrix.rows[i].getCoordinate(i);

            if (result == 0) {
                return 0;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Vector vector : rows) {
            sb.append(vector).append(',');
        }

        sb.delete(sb.length() - 1, sb.length()).append('}');

        return sb.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getCoordinatesCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество элементов в векторе должно совпадать с количеством столбцов матрице." +
                    "Количество столбцов в матрице = " + getColumnsCount()
                    + "Количество элементов в векторе = " + vector.getCoordinatesCount());
        }

        Vector resultVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            resultVector.setCoordinate(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        Matrix.checkMatrixParameters(this, matrix);
        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        Matrix.checkMatrixParameters(this, matrix);
        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {  // Todo проверить везде фильтры исключений
        Matrix.checkMatrixParameters(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix.checkMatrixParameters(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Кол-во столбцов в 1-ой матрице неравно количеству строк во второй матрице. Кол-во столбцов в 1-ой матрице = "
                    + matrix1.getColumnsCount() + ". Кол-ву строк во 2-ой матрице = " + matrix2.getRowsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                resultMatrix.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }

    private static void checkMatrixParameters(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() && matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Складывать/вычитать можно только одинаковы по размеру матрицы. Кол-во строк в 1-ой матрице = "
                    + matrix1.getRowsCount() + ". Во 2-ой = " + matrix2.getRowsCount() + "Кол-во столбцов в 1-ой матрице = " + matrix1.getColumnsCount()
                    + ". Во 2-ой = " + matrix2.getColumnsCount());
        }
    }
}