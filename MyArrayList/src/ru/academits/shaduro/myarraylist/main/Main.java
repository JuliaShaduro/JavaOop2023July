package ru.academits.shaduro.myarraylist.main;

import ru.academits.shaduro.myarraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> myArrayList1 = new ArrayList<>(1);

        myArrayList1.add(5);

        System.out.println("Размер списка №1 = " + myArrayList1.size());

        myArrayList1.add(1542);
        myArrayList1.add(796);
        myArrayList1.add(null);

        System.out.println("Список №1: " + myArrayList1);
        System.out.println("Индекс по значению = " + myArrayList1.indexOf(null));
        System.out.println("Последний индекс по значению = " + myArrayList1.lastIndexOf(112));
        System.out.println("Удаленный элемент = " + myArrayList1.remove(2));
        System.out.println("Список после удаления элемента = " + myArrayList1);
        System.out.println("------------------------");

        ArrayList<Integer> myArrayList2 = new ArrayList<>(0);
        myArrayList2.add(5);
        myArrayList2.add(1542);
        myArrayList2.add(796);
        myArrayList2.add(null);

        System.out.println("Список №2: " + myArrayList2);
        System.out.println("Размер списка №2 = " + myArrayList2.size());

        ArrayList<String> myArrayListString = new ArrayList<>();
        myArrayListString.add("Hello");
        myArrayListString.add(null);
        myArrayListString.add("coffee");
        myArrayListString.add("coffee");
        System.out.println("------------------------");

        String[] array = myArrayListString.toArray(new String[5]);

        System.out.println("Массив array: " + Arrays.toString(array));
        System.out.println("Длина массива array = " + array.length);

        myArrayListString.clear();

        System.out.println("Список String  после удаления всех элементов: " + myArrayListString);

        ArrayList<Long> arrayListLong = new ArrayList<>();
        arrayListLong.add(256633L);
        arrayListLong.add(1452214878L);

        Long[] arrayLong = new Long[5];

        myArrayList1.toArray(arrayLong);  //Todo нужно ли делать искл. ArrayStoreException?
    }
}