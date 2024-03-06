package ru.academits.shaduro.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 16;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Переданное значение capacity = " + initialCapacity + " , должно быть > 0");
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[initialCapacity];
    }

    private class ListIterator implements Iterator<E> {
        private int listIndex;
        private int listElementIndex;
        private final int initial = modCount;
        private int visitedElementsCount;

        public boolean hasNext() {
            return visitedElementsCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            if (initial != modCount) {
                throw new ConcurrentModificationException("Коллекция была изменена.");
            }

            if (lists[listIndex] == null || lists[listIndex].size() == listElementIndex) {
                listIndex++;
                listElementIndex = 0;

                while (lists[listIndex] == null) {
                    listIndex++;
                }
            }

            E element = lists[listIndex].get(listElementIndex);
            visitedElementsCount++;
            listElementIndex++;

            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (size == 0 || c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object element : c) {
            if (remove(element)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) { // удаляет элементы, не принадлежащие переданной коллекции
        if (size == 0 || c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int currentSize = list.size();

                if (list.retainAll(c)) {
                    size -= currentSize - list.size();
                    isRemoved = true;
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    private int getIndex(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(lists);
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                array[i] = element;
                ++i;
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length == 0) {
            throw new NullPointerException("Массив равен 0.");
        }

        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        return array;
    }
}