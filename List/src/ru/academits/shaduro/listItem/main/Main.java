package ru.academits.shaduro.listItem.main;

import ru.academits.shaduro.listItem.SinglyLinkedList;

public class Main {
    public static void main (String[] args) {
        SinglyLinkedList<Integer> numbersList = new SinglyLinkedList<>();
        numbersList.addFirst(5);
        numbersList.addFirst(1);
        numbersList.addFirst(44);
        numbersList.addFirst(129);

        System.out.println("Элемент по индексу = " + numbersList.get(3));
        numbersList.add(3, 22);
        numbersList.add(0, 22);

        System.out.println("Элемент по индексу = " + numbersList.get(1));
        System.out.println("Размер = " + numbersList.getCount());
        System.out.println("Удаление по индексу = " + numbersList.deleteByIndex(1));
        System.out.println("Элемент по индексу = " + numbersList.get(1));
        System.out.println("Первый элемент = " + numbersList.getFirst());
        System.out.println("Заменить значения элемента = " + numbersList.set(2, 17));
        System.out.println("Удалить элемент по значению = " + numbersList.delete(null));
        numbersList.reverse();
        System.out.println();
        SinglyLinkedList<Integer> copy = numbersList.copy();
        System.out.println("Размер copy листа = " + copy.getCount());
        System.out.println("Элемент по индексу = " + numbersList.get(4));
        System.out.println("Удалить элемент по значению = " + copy.delete(22));

        System.out.println(copy);
    }
}