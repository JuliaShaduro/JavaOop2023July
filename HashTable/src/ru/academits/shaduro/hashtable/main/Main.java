package ru.academits.shaduro.hashtable.main;

import ru.academits.shaduro.hashtable.HashTable;
import ru.academits.shaduro.shapes.Triangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>(3);

        hashTable1.add(null);
        hashTable1.add(7);
        hashTable1.add(375);
        hashTable1.add(15);
        hashTable1.add(3);
        hashTable1.add(101);

        for (Integer value : hashTable1) {
            System.out.println(value);
        }

        System.out.println("Таблица содержит элемент (true - да, false - нет) = " + hashTable1.contains(67));

        ArrayList<Integer> list = new ArrayList<>(7);
        list.add(7);
        list.add(375);
        list.add(null);

        System.out.println("Таблица №1 после добавления элементов: " + hashTable1);
        System.out.println("Удалены элементы в табл. №1, принадлежащие переданной коллекции (true - да, false - нет) = "
                + hashTable1.removeAll(list));
        System.out.println("Таблица №1 после удаления элементов: " + hashTable1);

        HashTable<Integer> hashTable2 = new HashTable<>();

        hashTable2.add(null);

        System.out.print("Элементы таблицы №2: ");

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
        System.out.println("Удалены элементы в табл. №1, не принадлежащие переданной коллекции (true - да, false - нет) = "
                + hashTable1.retainAll(list));
        System.out.println("Таблица №1 после удаления элементов: " + hashTable1);
        System.out.println("Количество элементов в таблице №1 после удаления = " + hashTable1.size());

        Object[] array1 = hashTable1.toArray();

        System.out.println("Массив объектов №1: " + Arrays.toString(array1));

        Integer[] array2 = hashTable1.toArray(new Integer[2]);
        System.out.println("Массив объектов №2: " + Arrays.toString(array2));

        hashTable1.clear();
        System.out.println("Clear таблицу №1 = " + hashTable1);
    }
}