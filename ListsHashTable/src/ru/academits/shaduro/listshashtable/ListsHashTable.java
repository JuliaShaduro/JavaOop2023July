package ru.academits.shaduro.listshashtable;

import java.util.*;

public class ListsHashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 16;

    public ListsHashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public ListsHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Переданное значение capacity = " + initialCapacity + " , должно быть > 0");
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[initialCapacity];
    }

    private class ListIterator implements Iterator<E> {
        private int listIndex;
        private final int startModCount = modCount;
        private int listElementIndex;
        private int visitedElementCount;

        public boolean hasNext() {
            return visitedElementCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция была одновременно изменена другим потоком.");
            }

            if (lists[listIndex].size() == listElementIndex) {
                listIndex++;
                listElementIndex = 0;
            }

            E element = lists[listIndex].get(listElementIndex);
            visitedElementCount++;
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

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkCollection(c);

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
        checkCollection(c);

        boolean isRemove = false;

        for (ArrayList<E> list : lists) {
            list.removeAll(c);
            isRemove = true;
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        boolean isRetain = false;

        for (ArrayList<E> list : lists) {
            list.retainAll(c);
            isRetain = true;
        }

        return isRetain;
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
        checkCollection(c);

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
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                //noinspection unchecked
                a[i] = (T1) element;
                i++;
            }
        }

        return a;
    }

    private void checkCollection(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция null.");
        }
    }
}
