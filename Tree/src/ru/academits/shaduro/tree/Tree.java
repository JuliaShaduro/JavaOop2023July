package ru.academits.shaduro.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {   //если implements Comparable<T>

    //не могу переопределить метод //    @Override   Почему (может потому что не примитивные типы) ? Тип объектов один.
    // можно переопределять когда примитивные?
    ////    public int compareTo(T o) {
    ////        if (head.getData() > o) {
    ////
    ////        }
    ////        return 0;
    ////    }
    private TreeNode<T> root;
    private int count;

    public void add(T data) {
        checkData(data);

        if (root == null) {
            root = new TreeNode<>(data);
            count++;
            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (currentNode.getData().compareTo(data) > 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));
                    count++;
                    return;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));
                    count++;
                    return;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean contains(T data) {
        checkData(data);

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData().compareTo(data) == 0) {
                return true;
            }

            if (currentNode.getData().compareTo(data) > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public boolean remove(T data) {
        checkData(data);

        if (data == root.getData()) {
            removeRoot();
            count--;
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> previousNode = null;
        boolean isLeft = true;

        while (currentNode != null) {
            int compareToResult = currentNode.getData().compareTo(data);

            if (compareToResult > 0) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
                isLeft = true;
            } else if (compareToResult < 0) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
                isLeft = false;
            } else {
                removeNode(isLeft, currentNode, previousNode);
                count--;

                return true;
            }
        }

        return false;
    }

    private void removeRoot() {
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }

        if (root.getRight() == null) {
            root = root.getLeft();
            return;
        }

        if (root.getLeft() == null) {
            root = root.getRight();
            return;
        }

        TreeNode<T> leftListParent = root.getRight();
        TreeNode<T> leftList;

        if (leftListParent.getLeft() == null) {
            leftListParent.setLeft(root.getLeft());
        } else {
            while (true) {
                leftList = leftListParent;
                leftListParent = leftListParent.getLeft();

                if (leftListParent.getLeft() == null) {
                    if (leftListParent.getRight() != null) {
                        leftList.setLeft(leftListParent.getRight());
                    } else {
                        leftList.setLeft(null);
                    }

                    leftListParent.setLeft(root.getLeft());
                    leftListParent.setRight(root.getRight());
                    break;
                }
            }
        }

        root = leftListParent;
    }

    private void removeNode(boolean isLeft, TreeNode<T> currentNode, TreeNode<T> previousNode) {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
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
            removeWithTwoChildren(currentNode, previousNode, isLeft);
        }
    }

    private void removeWithTwoChildren(TreeNode<T> currentNode, TreeNode<T> previousNode, boolean isLeft) {
        TreeNode<T> minLeftNodeParent = currentNode.getRight();
        TreeNode<T> minLeftNode = minLeftNodeParent.getLeft();

        if (minLeftNode == null) {
            if (!isLeft) {
                previousNode.setRight(minLeftNodeParent);
            } else {
                previousNode.setLeft(minLeftNodeParent);
            }

            minLeftNodeParent.setLeft(currentNode.getLeft());

            return;
        }

        while (minLeftNode.getLeft() != null) {
            minLeftNodeParent = minLeftNode;
            minLeftNode = minLeftNode.getLeft();
        }

        if (minLeftNode.getRight() != null) {
            minLeftNodeParent.setLeft(minLeftNode.getRight());
        } else {
            minLeftNodeParent.setLeft(null);
        }

        currentNode.setData(minLeftNode.getData());
    }

    public int size() {
        return count;
    }

    public void isBreadthSearching() {//обход в ширину
        if (root == null) {
            return;
        }

        TreeNode<T> currentNode = root;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            System.out.print(" " + queue.peek().getData());
            queue.remove();

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }

            currentNode = queue.peek();
        }
    }

    public void isDepthSearching() {//обход в глубину
        TreeNode<T> currentNode = root;
        Deque<TreeNode<T>> deque = new LinkedList<>();
        deque.add(currentNode);

        while (currentNode != null) {
            System.out.print(" " + deque.getLast().getData());
            deque.removeLast();

            if (currentNode.getRight() != null) {
                deque.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                deque.add(currentNode.getLeft());
            }

            currentNode = deque.peekLast();
        }
    }

    public void isDepthSearchingWithRecursive() {//обход в глубину с рекурсией
        isDepthSearching(root);
    }

    private void isDepthSearching(TreeNode<T> head) {
        if (head == null) {
            return;
        }

        System.out.print(head.getData() + " ");

        if (head.getLeft() != null) {
            isDepthSearching(head.getLeft());
        }

        if (head.getRight() != null) {
            isDepthSearching(head.getRight());
        }
    }

    private void checkData(T data) {
        if (data == null) {
            throw new NullPointerException("Передаваемое значение = null.");
        }
    }
}