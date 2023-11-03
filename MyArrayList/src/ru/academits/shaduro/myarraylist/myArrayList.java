package ru.academits.shaduro.myarraylist;

import java.util.*;


public class myArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public myArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity <= 0.");
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public myArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int iteratorModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Список пустой.");
            }

            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция была одновременно изменена другим потоком.");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public boolean add(T t) {
        if (size >= items.length) {  //Todo при каких обстоятельствах size может быть больше?
            ensureCapacity(items.length * 2);
        }

        items[size] = t;
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона {0: " + size + "}.");
        }

        if (size >= items.length) {
            ensureCapacity(items.length * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Передаваемая коллекция пустая.");
        }

        int newCollectionLength = c.size();

        if (newCollectionLength > (items.length - size)) {
            ensureCapacity(items.length + newCollectionLength);
        }

        for (Object o : c) {
            add((T) o); //todo передать все элементы совместимого типо дочер или родител. Проработать искл-я???
        }

        size += newCollectionLength;

        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона {0: " + size + "}.");
        }

        if (c.isEmpty()) {
            throw new NullPointerException("Передаваемая коллекция пустая.");
        }

        int newCollectionLength = c.size();

        if (newCollectionLength > (items.length - size)) {
            ensureCapacity(items.length + newCollectionLength); //Todo нужно ли вычитывать точный размер нов.кол-ции? или
            //Todo оставляем null [15, 5, 7, 125, 136, 125, 75, 86, 91, 12, 5, null, null, null, null, null]}
        }

        System.arraycopy(items, index, items, index + c.size(), size - index);

        for (int i = index, j = 0; i < index + c.size(); i++, j++) {
            add(i, (T) c.toArray()[j]);
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new NullPointerException("Список пустой.");
        }

        boolean result = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(items[i]);
                i--;

                result = true;  //Todo постоянно обращаемся к переменной !-
            }
        }

        return result;
    }

    public void ensureCapacity(int minCapacity) {
        //Todo если minCapacity меньше size? гарантирует, что вместимость списка
        //будет >= указанного числа. Если вместимость и так не меньше, то
        //ничего не будет сделано. Иначе – массив пересоздастся, но будет
        //иметь вместимость не менее указанной
        items = Arrays.copyOf(items, minCapacity);
    }

    public void trimToSize () {
        //Todo А если надо чтобы последний элемент null остался?
        items = Arrays.copyOf(items, size);
    }

    @Override
    public Iterator<T> iterator() {
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
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Список пустой.");
        }

        boolean result = false;

        for (Object o : c) {
            if (remove(o)) {
                //Todo постоянно обращаемся к переменной ?
                result = true;
            }
        }

        return result;
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
        //Todo проверить на null o
        return indexOf(o) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (size == 0) {
            throw new NullPointerException("Список пустой.");
        }

        for (Object o : c) {
            if (contains(o)) {
                continue;
            }

            return false;
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
    public <T1> T1[] toArray(T1[] a) { //Todo T и T1 - разные классы? T - родитель, a T1 - дочерний?Должен быть массив определенного типа данных
        if (a.length < size) {

            // System.arraycopy(items, 0, (T[])a, 0, size);

            a = (T1[]) Arrays.copyOf(items, size);
            return a;
        }

        a = (T1[]) Arrays.copyOf(items, a.length + 1);
        return a;
    }

    @Override
    public void clear() {
        if (size == 0) {
            throw new NullPointerException("Список пустой.");
        }

        modCount++;

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public T get(int index) {
        //Todo проверить сообщение об искл . Если список 0 . и вызывать 0 элемент.
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (size - 1) + "}" + ". Текущий индекс = " + index);
        }

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (size - 1) + "}"
                    + ". Текущий индекс = " + index);
        }

        return items[index] = element;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы {0; " + (size - 1) + "}"
                    + ". Текущий индекс = " + index);
        }

        T result = get(index);

        if (index <= size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        --size;
        modCount++;

        return result;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            index++;

            if (items[i].equals(o)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;

        for (int i = size - 1; i >= 0; i--) {
            index++;

            if (items[i].equals(o)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("myArrayList {");
        sb.append(items == null ? "null" : Arrays.asList(items).toString());
        sb.append('}');
        return sb.toString();
    }
}