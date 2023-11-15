package ru.academits.shaduro.matrix.main;

import ru.academits.shaduro.matrix.Matrix;
import ru.academits.shaduro.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector[] vectors1 = {
                new Vector(0.0, 0, 5),
                new Vector(4.0, 5, 0),
                new Vector(2.0, -7, 0)
        };

        Matrix matrix1 = new Matrix(vectors1);
        System.out.println("Определитель матрицы1 = " + matrix1.getDeterminant());

        Vector[] vectors2 = {
                new Vector(0.0, 0, 7, 2),
                new Vector(4.0, 5, 0),
                new Vector(2.0, -7, 0)
        };

        Matrix matrix2 = new Matrix(vectors2);

        System.out.println("Умножение матрицы1 на матрицу2 = " + Matrix.getProduct(matrix1, matrix2));

        matrix2.transpose();
        System.out.println("Транспонирование матрицы2 = " + matrix2);

        Vector[] vectors3 = {
                new Vector(0, 0.0),
                new Vector()
        };

        Matrix matrix3 = new Matrix(vectors3);

        double[][] array = {{}, {2, 3}}; // Todo корректность сообщения в искл?
        Matrix matrix4 = new Matrix(array);
    }
}