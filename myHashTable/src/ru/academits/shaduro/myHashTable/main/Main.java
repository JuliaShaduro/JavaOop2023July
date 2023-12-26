package ru.academits.shaduro.myHashTable.main;

import ru.academits.shaduro.myHashTable.myHashTable;
import ru.academits.shaduro.shapes.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        myHashTable<Integer> myHashTable = new myHashTable<>(16);

        myHashTable.add(5);
        myHashTable.add(150);

        System.out.println("Таблица после добавления 2-х элементов " + myHashTable);

        Triangle triangle = new Triangle(1, -5, 3, 155, 9, 14);
        myHashTable.add((int) triangle.getPerimeter());

        myHashTable.add(17);
        myHashTable.add(null);

        System.out.println("Таблица после добавления 3-х элементов " + myHashTable);
        System.out.println("Количество элементов в таблице = " + myHashTable.size());
        System.out.println("Результат удаления по значению = " + myHashTable.remove(64));

        Object[] array1 = myHashTable.toArray();
        System.out.println("Массив объектов №1: " + Arrays.toString(array1));

        Integer[] array2 = myHashTable.toArray(new Integer[10]);
        System.out.println("Массив объектов №2: " + Arrays.toString(array2));

        myHashTable.clear();
        System.out.println("Clear таблицу = " + myHashTable);
    }
}