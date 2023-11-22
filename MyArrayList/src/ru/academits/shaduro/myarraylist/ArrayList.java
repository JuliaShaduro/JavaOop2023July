package ru.academits.shaduro.myarraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше -1. Текущая вместимость = " + capacity);
        }
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int startModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция была одновременно изменена другим потоком.");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public boolean add(E data) {
        add(size, data);

        return true;
    }

    @Override
    public void add(int index, E data) {
        validationForAdd(index);

        if (size >= items.length) {
            ensureCapacity(items.length * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = data;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        validationForAdd(index);

        int inputCollectionSize = c.size();

        if (inputCollectionSize > (items.length - size)) {
            ensureCapacity(size + inputCollectionSize);
        }

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (E data : c) {
            items[i] = data;
            i++;

            size++;
        }

        modCount++;
        return true;
    }

    private void validationForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона {0: " + size + "}.");
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < items.length) {
            return;
        }

        if (minCapacity == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
            return;
        }

        items = Arrays.copyOf(items, minCapacity);
    }

    public void trimToSize() {
        if (size == items.length) {
            return;
        }

        modCount++;
        items = Arrays.copyOf(items, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);

                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isDeleted = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isDeleted = true;
            }
        }

        return isDeleted;
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
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        System.arraycopy(items, 0, result, 0, size);

        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection unchecked
        a = (T[]) Arrays.copyOf(items, a.length, a.getClass());

        if (a.length > size)
            a[size] = null; //Todo Для чего делать ?
        //Это полезно при определении длины списка, только если вызывающий объект знает, что список не содержит никаких null элементов.

        return a;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        modCount++;

        Arrays.fill(items, null);
        size = 0;
    }

    @Override
    public E get(int index) {
        validation(index);

        return items[index];
    }

    @Override
    public E set(int index, E data) {
        validation(index);

        E oldData = items[index];
        items[index] = data;

        return oldData;
    }

    @Override
    public E remove(int index) {
        validation(index);

        E result = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        --size;
        modCount++;

        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return ("myArrayList = {") + Arrays.asList(items) + '}';
    }

    private void validation(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Список пустой. Текущий индекс = " + index);
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (size - 1) + "}"
                    + ". Текущий индекс = " + index);
        }
    }
}