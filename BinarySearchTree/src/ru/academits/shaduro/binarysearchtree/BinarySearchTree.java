package ru.academits.shaduro.binarysearchtree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        comparator = null;
    }

    private int compare(E data1, E data2) {
        if (comparator == null) {
            if (data1 == null && data2 == null) {
                return 0;
            }

            if (data1 == null) {
                return -1;
            }

            if (data2 == null) {
                return 1;
            }

            //noinspection unchecked
            return ((Comparable<E>) data1).compareTo(data2);
        }

        return comparator.compare(data1, data2);
    }

    public int size() {
        return size;
    }

    public void add(E data) {
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
        TreeNode<E> currentNode = root;
        TreeNode<E> previousNode = null;
        boolean isLeft = true;

        while (currentNode != null) {
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
        TreeNode<E> removedNodeChild;

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            removedNodeChild = null;
        } else if (currentNode.getLeft() == null) {
            removedNodeChild = currentNode.getRight();
        } else if (currentNode.getRight() == null) {
            removedNodeChild = currentNode.getLeft();
        } else {
            removedNodeChild = removeNodeWithTwoChildren(currentNode);
        }

        if (isLeft) {
            if (previousNode == null) {
                root = removedNodeChild;
            } else {
                previousNode.setLeft(removedNodeChild);
            }
        } else {
            previousNode.setRight(removedNodeChild);
        }
    }

    private TreeNode<E> removeNodeWithTwoChildren(TreeNode<E> remotedNode) {
        TreeNode<E> minLeftNodeParent = remotedNode.getRight();
        TreeNode<E> minLeftNode = minLeftNodeParent.getLeft();

        if (minLeftNode == null) {
            minLeftNode = minLeftNodeParent;
            minLeftNode.setLeft(remotedNode.getLeft());
        } else {
            while (minLeftNode.getLeft() != null) {
                minLeftNodeParent = minLeftNode;
                minLeftNode = minLeftNode.getLeft();
            }

            minLeftNodeParent.setLeft(minLeftNode.getRight());
            minLeftNode.setLeft(remotedNode.getLeft());
            minLeftNode.setRight(remotedNode.getRight());
        }

        return minLeftNode;
    }

    public void visitInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> queueNode = queue.remove();
            consumer.accept(queueNode.getData());

            if (queueNode.getLeft() != null) {
                queue.add(queueNode.getLeft());
            }

            if (queueNode.getRight() != null) {
                queue.add(queueNode.getRight());
            }
        }
    }

    public void visitInDepth(Consumer<E> consumer) {
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

    public void visitInDepthWithRecursion(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        visitInDepth(root, consumer);
    }

    private void visitInDepth(TreeNode<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitInDepth(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitInDepth(node.getRight(), consumer);
        }
    }
}