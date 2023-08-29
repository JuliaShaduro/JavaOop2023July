package ru.academits.shaduro.matrix.main;

import ru.academits.shaduro.matrix.Matrix;
import ru.academits.shaduro.vector.Vector;

public class Main {
    public static void main (String[] args) {
        Vector[] vectors = new Vector[]{new Vector(36.0),
                new Vector(4.0, 5, 3.4),};

        Matrix matrix1 = new Matrix(vectors);

        Matrix matrix2 = new Matrix(2, 2);

        double[][] array = {{1,2.3,6,4.1}, {36}};
        Matrix matrix3 = new Matrix(array);

        System.out.println("Сумма двух матриц = " + Matrix.getSum(matrix1,matrix3));
        System.out.println("Разность матриц = " + Matrix.getDifference(matrix2,matrix1));
        System.out.println("Умножение матриц = " + Matrix.getMultiplication(matrix1, matrix2));
    }
}
