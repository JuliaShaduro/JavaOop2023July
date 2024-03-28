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

        Vector[] vectors2 = {
                new Vector(0.0, 7, 2),
                new Vector(4.0, 5, 0),
                new Vector(2.0, -7, 0)
        };

        Matrix matrix2 = new Matrix(vectors2);

        System.out.println("Определитель матрицы №2 = " + matrix2.getDeterminant());
        System.out.println("Matrix1: " + matrix1);
        System.out.println("Matrix2: " + matrix2);

        System.out.println("Умножение матрицы 1 на матрицу 2 = " + Matrix.getProduct(matrix1, matrix2));

        matrix2.transpose();
        System.out.println("Транспонирование матрицы 2 = " + matrix2);

        Vector[] vectors3 = {
                new Vector(1, 0),
                new Vector(1, 0)
        };

        Matrix matrix3 = new Matrix(vectors3);
        System.out.println("Матрица №3: " + matrix3);

        double[][] array = {{}, {}, {5}};

        Matrix matrix4 = new Matrix(array);
        System.out.println("Матрица №4: " + matrix4);

        Matrix matrix5 = new Matrix(matrix4);
        System.out.println("Матрица №5: " + matrix5);

        Matrix matrix6 = new Matrix(-1, -1);
        System.out.println("Матрица №6: " + matrix6);
    }
}