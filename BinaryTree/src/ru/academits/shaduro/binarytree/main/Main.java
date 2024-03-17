package ru.academits.shaduro.binarytree.main;

import ru.academits.shaduro.binarytree.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(13);
        binaryTree.add(24);
        binaryTree.add(6);
        binaryTree.add(5);
        binaryTree.add(10);
        binaryTree.add(15);
        binaryTree.add(27);
        binaryTree.add(25);
        binaryTree.add(26);
        binaryTree.add(14);

        System.out.println("Размер бинарного дерева = " + binaryTree.size());
        System.out.println("Удален элемент (true - да; false - нет) = " + binaryTree.remove(24));

        Consumer<Integer> consumer = data -> System.out.printf("%s ", data);

        System.out.println("Обход в ширину: ");

        binaryTree.visitInWidth(consumer);

        System.out.println();
        System.out.println("Обход в глубину: ");

        binaryTree.visitInDepth(consumer);

        System.out.println();
        System.out.println("Обход в глубину c рекурсией: ");

        binaryTree.visitInDeepWithRecursion(consumer);
    }
}