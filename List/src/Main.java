import ru.academits.shaduro.list.ListItem;
import ru.academits.shaduro.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListItem<String> list = new ListItem<>("четвертый", null);
        ListItem<String> list2 = new ListItem<>("третий", list);
        ListItem<String> list3 = new ListItem<>("второй", list2);
        ListItem<String> list4 = new ListItem<>("первый", list3);

        SinglyLinkedList<String> stringSinglyLinkedList = new SinglyLinkedList<>(list4);

        System.out.println("Размер списка = " + stringSinglyLinkedList.getSize());
        System.out.println("Первый элемент списка = " + stringSinglyLinkedList.getFirstElement());
        System.out.println("Получить элемент по индексу = " + stringSinglyLinkedList.getElement(3).getData());
        System.out.println("Изменить элемент по индексу = " + stringSinglyLinkedList.setValue(2, "ноль"));
        System.out.println("Удаление элемента по индексу = " + stringSinglyLinkedList.deleteElement(0));
        stringSinglyLinkedList.addElement(3, "шесть");
        System.out.println("Удаление элемента по значению = " + stringSinglyLinkedList.deleteByData("шесть"));
        stringSinglyLinkedList.reverse();

        SinglyLinkedList<String> copy = stringSinglyLinkedList.copyList();
        System.out.println("Первый элемент copy списка = " + copy.getFirstElement());
    }
}