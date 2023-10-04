package ru.academits.shaduro.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }
    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            count++;
        }
    }
    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        if (head == null) {
            throw new IllegalArgumentException("Список пустой.");
        }

        return head.getData();
    }
    public ListItem<T> getElement(int index) {
        this.checkIndex(index);

        ListItem<T> element = head;

        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }

        return element;
    }

    public T setValue(int index, T data) {
        this.checkIndex(index);

        ListItem<T> element = getElement(index);
        T oldData = element.getData();
        element.setData(data);

        return oldData;
    }

    public T deleteElement(int index) {
        this.checkIndex(index);

        if (index == 0) {
            return deleteFirstElement();
        }

        ListItem<T> element = getElement(index - 1);
        T temp = element.getNext().getData();
        element.setNext(element.getNext().getNext());

        count--;

        return temp;
    }

    public void addFirstElement(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void addElement(int index, T data) {
        this.checkIndex(index);

        ListItem<T> list = new ListItem<>(data, getElement(index - 1).getNext());

        getElement(index - 1).setNext(list);
        count++;
    }
    public boolean deleteByData(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                count--;

                if (prev != null) {
                    prev.setNext(p.getNext());
                    return true;
                }

                head = p.getNext();
                return true;
            }
        }

        return false;
    }

    public T deleteFirstElement() {
        T firstElement = head.getData();
        head = head.getNext();

        return firstElement;
    }

    public void reverse() {
        if (head == null) {
            throw new IllegalArgumentException("Список пустой.");
        }

        ListItem<T> reverse = null;
        ListItem<T> element = head;

        while (element != null) {
            ListItem<T> nextE = element.getNext();
            element.setNext(reverse);
            reverse = element;
            element = nextE;
        }

        head = reverse;
    }

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();
        copy.addFirstElement(head.getData());

        for (ListItem<T> p = head.getNext(), temp = copy.head; p != null; p = p.getNext()) {
            temp.setNext(new ListItem<>(p.getData()));
            temp = temp.getNext();
        }

        copy.count = count;

        return copy;
    }

    private void checkIndex(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Допустимые индексы { 0; " + (count - 1) + "}"
                    + ". Индекс = " + index);
        }
    }
}