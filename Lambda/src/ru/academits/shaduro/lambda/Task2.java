package ru.academits.shaduro.lambda;

import java.util.Scanner;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите порядковый номер числа Фибоначчи = ");

        Stream.iterate(new int[]{0, 1}, array -> new int[]{array[1], array[0] + array[1]})
                .limit(scanner.nextInt())
                .map(array -> array[0])
                .forEach(System.out::println);

        System.out.print("Введите кол-во элементов для вычисления корней = ");

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

        int elementCount = scanner.nextInt(); // Todo Если scanner.nextInt() напрямую указывать в limit то уходит в бесконечность. Это с чем связанно?
        // Todo логика вроде как в Фибоначчи.

        Stream.iterate(0, x -> x + 1)
                .limit(elementCount)
                .map(Math::sqrt)
                .forEach(number -> {
                    System.out.printf(" %.2f", number);
                    System.out.println();
                });
    }
}