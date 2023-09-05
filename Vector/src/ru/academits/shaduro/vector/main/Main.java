package ru.academits.shaduro.vector.main;

import ru.academits.shaduro.vector.Vector;

public class Main {
    public static void main (String[] args) {
        Vector vector1 = new Vector(3);
        vector1.multiplyByScalar(-3);
        System.out.println("Умножение вектора1 на скаляр = " + vector1);
        vector1.reverse();
        System.out.println("Разворот вектора1 = " + vector1);
        System.out.println("Длина вектора1 = " + vector1.getLength());
        System.out.println("Индекс компоненты = " + vector1.getCoordinate(0));
        System.out.println("-----------------------------------------");

        Vector vector2 = new Vector(3.3, 35, 75);
        vector2.multiplyByScalar(6);
        System.out.println("Умножение вектора2 на скаляр = " + vector2);
        vector2.reverse();
        System.out.println("Разворот вектора2 = " + vector2);
        System.out.println("Длина вектора2 = " + vector2.getLength());
        System.out.println("Индекс компоненты = " + vector2.getCoordinate(2));
        System.out.println("-----------------------------------------");

        Vector vector3 = new Vector(3, 3.3, 17, 23);
        vector3.multiplyByScalar(12);
        System.out.println("Умножение вектора3 на скаляр = " + vector3);
        vector3.reverse();
        System.out.println("Разворот вектора3 = " + vector3);
        System.out.println("Длина вектора3 = " + vector3.getLength());
        System.out.println("Индекс компоненты = " + vector3.getCoordinate(2));
        vector3.setCoordinate(1, 36);
        System.out.println("Установка компоненты вектора по индексу" + vector3);
        System.out.println("-----------------------------------------");

        Vector copy = new Vector(vector2);
        System.out.println("Копия = " + copy);

        System.out.println("Сумма двух векторов = " + Vector.getSum(vector1, vector2));
        System.out.println("Разность векторов = " + Vector.getDifference(vector3, copy));
        System.out.println("Скалярное произведение векторов = " + Vector.getScalarProduct(copy, vector1));
    }
}