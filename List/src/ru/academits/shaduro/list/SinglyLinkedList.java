package ru.academits.shaduro.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пустой.");
        }

        return head.getData();
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public E get(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        E oldData = getItem(index).getData();
        getItem(index).setData(data);

        return oldData;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        E deletedData = previousItem.getNext().getData();

        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return deletedData;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + count + "}"
                    + ". Индекс = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                getItem(i - 1).setNext(new ListItem<>(data, getItem(i)));

                count++;
                return;
            }
        }
    }

    public boolean delete(E data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            deleteFirst();
            return true;
        }

        for (ListItem<E> currentItem = head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
                previousItem.setNext(currentItem.getNext());
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

        E deletedData = head.getData();
        head = head.getNext();

        count--;

        return deletedData;
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
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        copy.addFirst(head.getData());

        for (ListItem<E> currentItem = head.getNext(), copyItem = copy.head; currentItem != null; currentItem = currentItem.getNext()) {
            copyItem.setNext(new ListItem<>(currentItem.getData()));
            copyItem = copyItem.getNext();
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
        if (count == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append('{');

        ListItem<E> currentItem = head;

        for (int i = 0; i < count - 1; i++) {
            sb.append(currentItem.getData()).append(", ");
            currentItem = currentItem.getNext();
        }

        sb.append(currentItem.getData()).append('}');

        return sb.toString();
    }
}