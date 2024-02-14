package ru.academits.shaduro.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше или равна 0. Текущая вместимость = " + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    private class ArrayListIterator implements Iterator<E> {
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
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public void add(int index, E item) {
        checkIndexForAdd(index);

        if (size >= items.length) {
            ensureCapacity(items.length);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /*
6. ensureCapacity(int minCapacity):
- if (minCapacity == 0) - этой логики быть не должно - Todo почему? Если созданный список с 0 размером new ArrayList<>(0), вроде логично по DEFAULT сделать размер ;
*/
    public void ensureCapacity(int capacity) {
        if (capacity == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
            return;
        }

        items = Arrays.copyOf(items, capacity * 2);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkCollection(c);

            /*5. addAll с индексом:
- if (inputCollectionSize > (items.length - size)) - эта проверка не нужна, - /Todo почему она не нужна ? Если нам хватает места, для чего использовать метод capacity?
аналогичная проверка должна быть внутри самого метода ensureCapacity
- size - можно изменить за 1 раз*/
        checkIndexForAdd(index);

        if (c.size() > (items.length - size)) {
            ensureCapacity(c.size() + size);  //Todo создается в 2-а раза больше массив . Возможно, должна быть точный размер из 2-списков?
        }

        System.arraycopy(items, index, items, index + c.size(), size - index);

        for (E item : c) {
            items[index] = item;
            index++;
        }

        size += c.size();

        modCount++;
        return true;
    }

    public void trimToSize() {
        if (size == items.length) {
            return;
        }

        items = Arrays.copyOf(items, size);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removeItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        size--;
        modCount++;

        return removeItem;
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
        checkCollection(c);

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollection(c);

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
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
        checkCollection(c);

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {  // преобразуем список элементов в массив
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            a[i] = (T) items[i];
        }

        return a;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, null);
        size = 0;
        modCount++;
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
        StringBuilder st = new StringBuilder().append('[');

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                st.append(items[i]).append(", ");
            }

            st.delete(st.length() - 2, st.length());
        }

        return st.append(']').toString();
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона {0: " + size + "}.");
        }
    }

    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Список пустой. Текущий индекс = " + index);
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (size - 1) + "}"
                    + ". Текущий индекс = " + index);
        }
    }

    private void checkCollection(Collection<?> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Входящая коллекция пустая.");
        }
    }
}