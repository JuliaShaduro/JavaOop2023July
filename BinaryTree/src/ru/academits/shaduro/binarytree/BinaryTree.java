package ru.academits.shaduro.binarytree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> root;
    private int size;
    private Comparator<? super E> comparator;

    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree() {
    }

    private int compare(E data1, E data2) {
        if (data1 == null && data2 != null) {
            return -1;
        }

        if (data1 != null && data2 == null) {
            return 1;
        }

        if (data1 == null) {
            return 0;
        }

        if (comparator == null) {
            //noinspection unchecked
            return ((Comparable<E>) data1).compareTo(data2);
        }

        return comparator.compare(data1, data2);
    }

    public int size() {
        return size;
    }

    public void add(E data) {
        /*Вставка значений NULL в TreeSet вызывает исключение NullPointerException
         , поскольку при вставке значения NULL оно сравнивается с существующими элементами,
         а значение NULL нельзя сравнивать ни с каким значением.*/
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (compare(currentNode.getData(), data) > 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean contains(E data) {
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public boolean remove(E data) {
        if (size == 0) {
            return false;
        }

        if (compare(root.getData(), data) == 0) {
            if (size == 1) {
                root = null;
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else if (root.getRight() == null) {
                root = root.getLeft();
            } else {
                root = removeNodeWithTwoChildren(root);
            }

            size--;
            return true;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> previousNode = null;
        boolean isLeft = true;

        while (currentNode != null) { //Todo повторная проверка !
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult > 0) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
                isLeft = true;
            } else if (comparisonResult < 0) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
                isLeft = false;
            } else {
                removeNode(isLeft, currentNode, previousNode);
                size--;

                return true;
            }
        }

        return false;
    }

    private void removeNode(boolean isLeft, TreeNode<E> currentNode, TreeNode<E> previousNode) {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {// Todo не нравится этот метод. Постоянно проверяю сторону вставки. Что поменять?
            if (isLeft) {
                previousNode.setLeft(null);
            } else {
                previousNode.setRight(null);
            }
        } else if (currentNode.getLeft() == null) {
            if (isLeft) {
                previousNode.setLeft(currentNode.getRight());
            } else {
                previousNode.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight() == null) {
            if (isLeft) {
                previousNode.setLeft(currentNode.getLeft());
            } else {
                previousNode.setRight(currentNode.getLeft());
            }
        } else {
            if (isLeft) {
                previousNode.setLeft(removeNodeWithTwoChildren(currentNode));
                return;
            }

            previousNode.setRight(removeNodeWithTwoChildren(currentNode));
        }
    }

    private TreeNode<E> removeNodeWithTwoChildren(TreeNode<E> deleteNode) {
        TreeNode<E> minLeftNodeParent = deleteNode.getRight();
        TreeNode<E> minLeftNode = minLeftNodeParent.getLeft();

        if (minLeftNode != null) {
            while (minLeftNode.getLeft() != null) {
                minLeftNodeParent = minLeftNode;
                minLeftNode = minLeftNode.getLeft();
            }

            if (minLeftNode.getRight() != null) {
                minLeftNodeParent.setLeft(minLeftNode.getRight());
            } else {
                minLeftNodeParent.setLeft(null);
            }

            minLeftNode.setLeft(deleteNode.getLeft());
            minLeftNode.setRight(deleteNode.getRight());
        } else {
            minLeftNode = minLeftNodeParent;
            minLeftNode.setLeft(deleteNode.getLeft());
        }

        return minLeftNode;
    }

    public void visitInWidth(Consumer<E> consumer) {   //обход в ширину
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> queueNode = queue.remove();
            consumer.accept(queueNode.getData());

            if (queueNode.getLeft() != null) {
                queue.offer(queueNode.getLeft());
            }

            if (queueNode.getRight() != null) {
                queue.offer(queueNode.getRight());
            }
        }
    }

    public void visitInDepth(Consumer<E> consumer) { //обход в глубину
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> deque = new LinkedList<>();
        deque.addLast(root);

        while (!deque.isEmpty()) {
            TreeNode<E> dequeNode = deque.removeLast();
            consumer.accept(dequeNode.getData());

            if (dequeNode.getRight() != null) {
                deque.addLast(dequeNode.getRight());
            }

            if (dequeNode.getLeft() != null) {
                deque.addLast(dequeNode.getLeft());
            }
        }
    }

    public void visitInDeepWithRecursion(Consumer<E> consumer) {//обход в глубину с рекурсией
        if (root == null) {
            return;
        }

        visitInDepth(root, consumer);
    }

    private void visitInDepth(TreeNode<E> node, Consumer<E> consume) {
        consume.accept(node.getData());

        if (node.getLeft() != null) {
            visitInDepth(node.getLeft(), consume);
        }

        if (node.getRight() != null) {
            visitInDepth(node.getRight(), consume);
        }
    }
}