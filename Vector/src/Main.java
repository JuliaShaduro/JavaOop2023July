import ru.academits.shaduro.vector.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(1);
        double[] array = {1.5, 3};
        vector1.setArray(array);
        System.out.println("Умножение вектора1 на скаляр" + Arrays.toString(vector1.getScalarProduct(-3)));
        System.out.println("Разворот вектора1 = " + Arrays.toString(vector1.getVectorTurnaround()));
        System.out.println("Длина вектора1 = " + vector1.getVectorLength());
        System.out.println("Индекс компоненты = " + vector1.getIndex(2));
        System.out.println("-----------------------------------------");

        Vector vector2 = new Vector(3.3, 35, 75);
        System.out.println("Умножение вектора2 на скаляр" + Arrays.toString(vector2.getScalarProduct(-3)));
        System.out.println("Разворот вектора2 = " + Arrays.toString(vector2.getVectorTurnaround()));
        System.out.println("Длина вектора2 = " + vector2.getVectorLength());
        System.out.println("Индекс компоненты = " + vector2.getIndex(2));
        System.out.println("-----------------------------------------");

        Vector vector3 = new Vector(3, 3.3, 17, 23);
        System.out.println("Умножение вектора3 на скаляр" + Arrays.toString(vector3.getScalarProduct(-3)));
        System.out.println("Разворот вектора3 = " + Arrays.toString(vector3.getVectorTurnaround()));
        System.out.println("Длина вектора3 = " + vector3.getVectorLength());
        System.out.println("Индекс компоненты = " + vector3.getIndex(2));
        System.out.println("Установка компоненты вектора по индексу" + Arrays.toString(vector3.setCoordinates(2, 36)));
        System.out.println("-----------------------------------------");

        Vector copy = new Vector(vector1);

        System.out.println("Сложение двух векторов = " + Vector.getVectorsSum(vector1, vector2));
        System.out.println("Вычитание векторов = " + Vector.getVectorsDifference(vector2, copy));
        System.out.println("Скалярное произведение векторов = " + Vector.getVectorsScalarProduct(vector2, copy));
    }
}