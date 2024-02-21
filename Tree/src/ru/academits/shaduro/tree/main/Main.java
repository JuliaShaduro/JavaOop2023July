package ru.academits.shaduro.tree.main;

import ru.academits.shaduro.tree.Tree;

public class Main {
    public static void main(String[] args) {
        //Свой классы можем хранить только если реализуем Comparable<T>?
        Tree<Integer> tree = new Tree<>();
        tree.add(13);
        tree.add(8);
        tree.add(18);
        tree.add(6);
        tree.add(7);
        tree.add(11);
        tree.add(24);
        tree.add(14);
        tree.add(25);
        tree.add(12);
        tree.add(22);
        tree.add(23);
        tree.add(19);
        tree.add(5);
        tree.add(3);
        tree.add(10);

        System.out.println("Поиск узла по значению = " + tree.contains(5));
        System.out.println("Удаление по значению = " + tree.remove(6));

        System.out.println("Размер списка = " + tree.size());

        System.out.print("Обход в ширину:");
        tree.isBreadthSearching();

        System.out.println();

        System.out.print("Обход в глубину:");
        tree.isDepthSearching();

        System.out.println();

        System.out.print("Обход в глубину c рекурсией:");
        tree.isDepthSearchingWithRecursive();


    }
}