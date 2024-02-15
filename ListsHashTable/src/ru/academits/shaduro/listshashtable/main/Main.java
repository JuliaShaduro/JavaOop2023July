package ru.academits.shaduro.listshashtable.main;

import ru.academits.shaduro.listshashtable.ListsHashTable;
import ru.academits.shaduro.shapes.Triangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ListsHashTable<Integer> hashTable1 = new ListsHashTable<>(5);

        hashTable1.add(5);
        hashTable1.add(150);

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(23, 56, null, 1, 7));

        System.out.println("Вставка коллекции в таблицу = " + hashTable1.addAll(arrayList));

        ListsHashTable<Integer> hashTable2 = new ListsHashTable<>();

        hashTable2.add(null);

        System.out.print("Элементы списка №2: ");

        for (Integer integer : hashTable2) {
            System.out.print(integer + " ");
        }

        System.out.println();

        Triangle triangle = new Triangle(1, -5, 3, 155, 9, 14);
        hashTable1.add((int) triangle.getPerimeter());
        hashTable1.add(17);
        hashTable1.add(null);

        System.out.println("Таблица №1 после добавления элементов: " + hashTable1);
        System.out.println("Количество элементов в таблице №1 = " + hashTable1.size());
        System.out.println("Результат удаления по значению в таблице №1 = " + hashTable1.remove(64));

        Object[] array1 = hashTable1.toArray();
        System.out.println("Массив объектов №1: " + Arrays.toString(array1));

        Integer[] array2 = hashTable1.toArray(new Integer[12]);
        System.out.println("Массив объектов №2: " + Arrays.toString(array2));

        hashTable1.clear();
        System.out.println("Clear таблицу №1 = " + hashTable1);
    }
}