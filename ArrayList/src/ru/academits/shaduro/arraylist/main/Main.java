package ru.academits.shaduro.arraylist.main;

import ru.academits.shaduro.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>(0);

        list1.add(16);
        System.out.println("Список №1: " + list1);
        System.out.println("Размер списка №1 = " + list1.size());

        list1.add(5);
        list1.add(21);
        list1.add(45);
        list1.add(null);

        list1.add(4, 17);

        for (Integer value : list1) {
            System.out.print(value + " ");
        }

        System.out.println();
        System.out.println("Список №1: " + list1);
        System.out.println("Индекс по значению = " + list1.indexOf(null));
        System.out.println("Последний индекс по значению = " + list1.lastIndexOf(5));
        System.out.println("Удаленный элемент = " + list1.remove(2));
        System.out.println("Список №1 после удаления элемента = " + list1);
        System.out.println("------------------------");

        ArrayList<Integer> list2 = new ArrayList<>(0);
        list2.add(17);
        list2.add(1542);
        list2.add(796);
        list2.add(null);

        Object[] array1 = list2.toArray();
        System.out.println("toArray(): " + Arrays.toString(array1));

        Integer[] array2 = list2.toArray(new Integer[5]);
        System.out.println("toArray(T[] a integers1): " + Arrays.toString(array2));

        Number[] array3 = list2.toArray(new Integer[8]);
        System.out.println("toArray(T[] a integers2): " + Arrays.toString(array3));

        list1.addAll(5, list2);
        System.out.println("Список №1 после добавления списка №2: " + list1);
        System.out.println("Размер списка №1 = " + list1.size());

        list2.add(0, 16);

        System.out.println("Список №2: " + list2);
        System.out.println("Размер списка №2 = " + list2.size());

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Hello");
        list3.add(null);
        list3.add("coffee");
        list3.add("coffee");
        System.out.println("------------------------");

        String[] stringsArray = list3.toArray(new String[3]);

        System.out.println("Массив array: " + Arrays.toString(stringsArray));
        System.out.println("Длина массива array = " + stringsArray.length);

        list3.clear();

        System.out.println("Список String после удаления всех элементов: " + list3);

        ArrayList<Long> longsList = new ArrayList<>();
        longsList.add(256633L);
        longsList.add(1452214878L);

        System.out.println("Массив Long: " + longsList);

        Long[] arrayLong = new Long[5];
        list1.toArray(arrayLong);
    }
}