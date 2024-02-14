package ru.academits.shaduro.arraylist.main;

import ru.academits.shaduro.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbersFirstList = new ArrayList<>(3);

        numbersFirstList.add(16);
        System.out.println("Список №1: " + numbersFirstList);
        System.out.println("Размер списка №1 = " + numbersFirstList.size());

        numbersFirstList.add(5);
        numbersFirstList.add(21);
        numbersFirstList.add(45);
        numbersFirstList.add(null);
        numbersFirstList.add(5);

        System.out.println("Список №1: " + numbersFirstList);
        System.out.println("Индекс по значению = " + numbersFirstList.indexOf(null));
        System.out.println("Последний индекс по значению = " + numbersFirstList.lastIndexOf(5));
        System.out.println("Удаленный элемент = " + numbersFirstList.remove(2));
        System.out.println("Список №1 после удаления элемента = " + numbersFirstList);
        System.out.println("------------------------");


        ArrayList<Integer> numbersSecondList = new ArrayList<>(0);
        numbersSecondList.add(5);
        numbersSecondList.add(1542);
        numbersSecondList.add(796);
        numbersSecondList.add(null);

        Object[] objectsArray = numbersSecondList.toArray();
        System.out.println("toArray(): " + Arrays.toString(objectsArray));

        Integer[] integersFirstArray = numbersSecondList.toArray(new Integer[6]);
        System.out.println("toArray(T[] a integers1): " + Arrays.toString(integersFirstArray));

        Number[] integersSecondArray = numbersSecondList.toArray(new Integer[8]);
        System.out.println("toArray(T[] a integers2): " + Arrays.toString(integersSecondArray));

        numbersFirstList.addAll(2, numbersSecondList);
        System.out.println("Список №1 после добавления списка №2: " + numbersFirstList);
        System.out.println("Размер списка №1 = " + numbersFirstList.size());

        numbersSecondList.add(0,16);

        System.out.println("Список №2: " + numbersSecondList);
        System.out.println("Размер списка №2 = " + numbersSecondList.size());

        ArrayList<String> stringsList = new ArrayList<>();
        stringsList.add("Hello");
        stringsList.add(null);
        stringsList.add("coffee");
        stringsList.add("coffee");
        System.out.println("------------------------");

        String[] stringsArray = stringsList.toArray(new String[3]);

        System.out.println("Массив array: " + Arrays.toString(stringsArray));
        System.out.println("Длина массива array = " + stringsArray.length);

        stringsList.clear();

        System.out.println("Список String  после удаления всех элементов: " + stringsList);

        ArrayList<Long> longsList = new ArrayList<>();
        longsList.add(256633L);
        longsList.add(1452214878L);

        System.out.println("Массив Long: " + longsList);

        Long[] arrayLong = new Long[5];

        /*
- есть warning'и*/

        numbersFirstList.toArray(arrayLong);  //Todo нужно ли бросать искл. ArrayStoreException?
    }
}