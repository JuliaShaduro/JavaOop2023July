package ru.academits.shaduro.lambda;

import java.util.Scanner;
import java.util.stream.Stream;

/*Создать бесконечный поток корней чисел. С консоли
прочитать число – сколько элементов нужно вычислить,
затем – распечатать эти элементы.
*/
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите порядковый номер числа Фибоначчи = ");

        Stream.iterate(new int[]{0, 1}, array -> new int[]{array[1], array[0] + array[1]})
                .limit(scanner.nextInt())
                .map(array -> array [0])
                .forEach(System.out::println);

        System.out.println("___________");
// Todo Какая еще структура данных может подойти под эту задачу ?

//        Пробник # 2
//        List<Integer> list1 = new ArrayList<>(Arrays.asList(0, 1));
//        System.out.println(list1);
//
//        int[] stream = IntStream.iterate(0, x -> {
//                    int r = list1.get(list1.size() - 2) + list1.get(list1.size() - 1);
//                    list1.add(r);
//
//                    return r;
//                }).limit(8)
//                .toArray();
//
//        System.out.println(Arrays.toString(stream));
    }
}

