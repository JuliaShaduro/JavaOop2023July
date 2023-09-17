package ru.academits.shaduro.matrix.main;

import ru.academits.shaduro.matrix.Matrix;
import ru.academits.shaduro.vector.Vector;

public class Main {
    public static void main (String[] args) {
        Vector[] vectors = {
                new Vector(36.0),
                new Vector(4.0, 5, 3.4)
        };

        Matrix matrix1 = new Matrix(vectors);

        Matrix matrix2 = new Matrix(2, 2);

        double[][] array = {{1, 78, 6, 4.1}, {36, 23}};
        double[][] array1 = {{12}, {4}};
        Matrix matrixWithArray1 = new Matrix(array1);

        Matrix matrix3 = new Matrix(array);
        Matrix matrix4 = new Matrix(matrix3);

        System.out.println("matrix4 копия = " + matrix4);

        //   matrix3.multiplyByScalar(3);
        System.out.println(matrix3);

        array = new double[][]{{0, 0, 4, 3},
                {0, 0, 1, 5},
                {0, 0, 0, 2},
                {0, 4, 4, 0}};
        matrix3 = new Matrix(array);
        System.out.println(matrix3.getRow(1));

        System.out.println("getDeterminant = " + matrix3.getDeterminant());


       // System.out.println("умножение" + matrixWithArray1.multiplyByVector(new Vector(36.0, 3, 64)));

        Vector[] vectors1 = {
                new Vector(1.0, 0),
                new Vector(2.0, 1),
               new Vector(-1.0, 1),
           //     new Vector(0.0, 4, 0, 0),
        };

        Vector[] vectors2 = {
                new Vector(1.0,2,0),
                new Vector(0.0,-1,1),
               // new Vector(7.0, 1,8),
             //   new Vector(1.0, 5, 8, 6),
        };

        Matrix matrix6 = new Matrix(vectors1);
        Matrix matrix7 = new Matrix(vectors2);

        Vector vector3 = new Vector(8.0,7,2);

        System.out.println(matrix7.multiplyByVector(vector3));

        System.out.println("Ум" + Matrix.getProduct(matrix6, matrix7));

//        System.out.println("Сумма двух матриц = " + Matrix.getSum(matrix1, matrix3));
//        System.out.println("Разность матриц = " + Matrix.getDifference(matrix2, matrix1));
//        System.out.println("Умножение матриц = " + Matrix.getMultiplication(matrix1, matrix2));
    }
}
