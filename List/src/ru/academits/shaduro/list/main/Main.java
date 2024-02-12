package ru.academits.shaduro.list.main;

import ru.academits.shaduro.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> numbersList = new SinglyLinkedList<>();
        numbersList.addFirst(5);
        numbersList.addFirst(1);
        numbersList.addFirst(44);
        numbersList.addFirst(129);

        System.out.println("Элемент по индексу = " + numbersList.get(0));

        numbersList.add(1, 15);
        numbersList.add(5, 2);

        System.out.println("Список после добавления элемента по индексу = " + numbersList);

        System.out.println("Удалить элемент по значению = " + numbersList.delete(2));
        System.out.println("Список после удаления элемента по значению = " + numbersList);

        System.out.println("Размер = " + numbersList.getCount());
        System.out.println("Удаление по индексу = " + numbersList.deleteByIndex(1));
        System.out.println("Элемент по индексу = " + numbersList.get(1));
        System.out.println("Первый элемент = " + numbersList.getFirst());
        System.out.println("Заменить значения элемента = " + numbersList.set(2, 17));
        numbersList.reverse();
        System.out.println();

        SinglyLinkedList<Integer> copy = numbersList.copy();
        System.out.println("Размер copy списка = " + copy.getCount());
        System.out.println("Элемент по индексу = " + numbersList.get(3));
        System.out.println("Удалить элемент по значению = " + copy.delete(22));

        System.out.println("Копия списка: " + copy);
    }
}