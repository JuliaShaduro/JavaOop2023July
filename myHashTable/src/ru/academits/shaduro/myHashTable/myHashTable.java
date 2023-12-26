package ru.academits.shaduro.myHashTable;

import java.util.*;

public class myHashTable<T> implements Collection<T> {
    private final ArrayList<T>[] hashTable;
    int size;
    int modCount;
    private static final int DEFAULT_CAPACITY = 16;

    public myHashTable() {
        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public myHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Переданное значение capacity = " + initialCapacity + " , должно быть > 0");
        }

        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[initialCapacity];
    }

    private class MyListIterator implements Iterator<T> {  // для какой цели в этой задачи итератор
        private int currentIndex = -1;
        private final int startModCount = modCount;
        private int indexInArray = 0;

        public boolean hasNext() {
            return currentIndex + 1 < hashTable.length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция была одновременно изменена другим потоком.");
            }

            currentIndex++;

            while (hashTable[currentIndex] != null) {
                if (indexInArray < hashTable[currentIndex].size()) {
                    T data = hashTable[currentIndex].get(indexInArray);
                    currentIndex--;
                    indexInArray++;

                    return data;
                }

                indexInArray = 0;
                currentIndex++;
            }

            return null;
        }
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        if (index >= hashTable.length || hashTable[index] == null) {
            return false;
        }

        return hashTable[index].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция null.");
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

        if (index >= hashTable.length || hashTable[index] == null) {
            return false;
        }

        if (hashTable[index].remove(o)) {
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция null.");
        }

        for (Object e : c) {
            if (!remove(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);
        //Todo если индекс больше initialCapacity.
        // Создаем новый ArrayList<T>[] - копируем данные - item ссылаем на новый Array? Такие ситуации редки?  задавать Capacity больше 100? Все равно может не хватить
//        if (index >= items.length) {
//            Arrays.copyOf(items,index+1);
//        }

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        hashTable[index].add(t);

        ++size;   //Todo это кол-во элементов countData?
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция null.");
        }

        for (T t : c) {
            if (!add(t)) {
                return false;
            }
        }

        return true;
    }

    private int getIndex(Object o) {
        return Math.abs((o == null) ? 0 : o.hashCode() % hashTable.length);
    }

    @Override
    public String toString() {
        return ("myHashTable: {" + Arrays.asList(hashTable) + "}");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция null.");
        }

        boolean isDeleted = false;

        for (Object e : c) {
            if (!contains(e)) {
                remove(e);
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        int i = 0;

        while (i < hashTable.length) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }

            i++;
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
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (ArrayList<T> element : hashTable) {
            if (element == null) { // или null тоже должны быть ?
                continue;
            }

            for (T data : element) {
                array[i] = data;
                ++i;
            }
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size);
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}