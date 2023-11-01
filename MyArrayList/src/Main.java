import ru.academits.shaduro.myarraylist.myArrayList;

public class Main {
    public static void main (String[] args) {
        myArrayList<Integer> myArrayList1 = new myArrayList<>();
        myArrayList1.add(5);
        myArrayList1.add(7);
        myArrayList1.add(12);
        myArrayList1.add(5);

        System.out.println(myArrayList1);
        System.out.println("Удаленный элемент = " + myArrayList1.remove(2));
        System.out.println("Коллекция после удаления элемента = " + myArrayList1);

        myArrayList<Integer> myArrayList2 = new myArrayList<>(5);
        myArrayList2.add(75);
        myArrayList2.add(1542);
        myArrayList2.add(796);
        myArrayList2.add(589);

        myArrayList1.removeAll(myArrayList2);
    }
}