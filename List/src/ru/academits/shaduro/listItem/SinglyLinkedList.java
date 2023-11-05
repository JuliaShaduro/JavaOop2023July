package ru.academits.shaduro.listItem;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пустой.");
        }

        return head.getData();
    }

    private ListItem<E> foundNodeByIndex(int index) {//Todo может быть метод избыточен?
        ListItem<E> item = head;

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                break;
            }

            item = item.getNext();
        }

        return item;
    }

    /*8. getElement(int index):
   - этот метод должен быть приватным. -//
    //Todo После исправления ошибок он не вспомогательный и не выдает узел => может быть неприватным?
   И, в целом, вспомогательные методы должны быть приватными
   - здесь лучше не делать проверку индекса, ее лучше выполнять в методах, -
    вызывающих этот метод, т.к. индекс, передаваемый в эти методы, не всегда совпадает с индексом, передаваемым в getElement

    */
    public E get(int index) {
        checkIndex(index);

        return foundNodeByIndex(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> node = foundNodeByIndex(index);
        E oldItem = node.getData();
        node.setData(data);

        return oldItem;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem1 = foundNodeByIndex(index - 1);
        E deleteItem1 = previousItem1.getNext().getData();

        previousItem1.setNext(previousItem1.getNext().getNext());
        count--;

        return deleteItem1;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(int index, E data) {
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (count) + "}"
                    + ". Индекс = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> newNode = new ListItem<>(data);
        ListItem<E> previousNode = head;

        for (int i = 0; i <= index; i++) {
            if (i == index - 1) {
                if (i == count - 1) {
                    previousNode.setNext(newNode);

                    count++;
                    return;
                }

                newNode.setNext(previousNode.getNext());
                previousNode.setNext(newNode);
                count++;
                return;
            }

            previousNode = previousNode.getNext();
        }
    }

    public boolean delete(E data) {
        for (ListItem<E> currentNode = head, previousNode = null; currentNode != null;
             previousNode = currentNode, currentNode = currentNode.getNext()) {

            if (Objects.equals(data, head.getData())) {
                deleteFirst();
                return true;
            }

            if (Objects.equals(data, currentNode.getData())) {
                previousNode.setNext(previousNode.getNext().getNext()); // Todo можно ли заглушить ошибку?
                count--;

                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой.");
        }

        E deleteItem = head.getData();
        head = head.getNext();

        count--;

        return deleteItem;
    }

    public void reverse() {
        ListItem<E> previousItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой.");
        }

        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        copy.addFirst(head.getData());

        for (ListItem<E> currentNode = head.getNext(), copyNode = copy.head; currentNode != null; currentNode = currentNode.getNext()) {
            copyNode.setNext(new ListItem<>(currentNode.getData()));
            copyNode = copyNode.getNext();
        }

        copy.count = count;

        return copy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (count - 1) + "}"
                    + ". Индекс = " + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        ListItem<E> currentElement = head;

        for (int i = 0; i < count - 1; i++) {
            sb.append(currentElement.getData()).append(", ");
            currentElement = currentElement.getNext();
        }

        sb.append(currentElement.getData());
        sb.append('}');

        return sb.toString();
    }
}