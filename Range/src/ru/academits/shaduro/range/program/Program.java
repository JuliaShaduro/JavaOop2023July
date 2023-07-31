package ru.academits.shaduro.range.program;

import ru.academits.shaduro.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите 1-ое число интервала = ");
        double from = scanner.nextDouble();

        System.out.print("Введите 2-ое число интервала = ");
        double to = scanner.nextDouble();

        System.out.print("Введите число = ");
        double number = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.println("Длина 1-ого диапазона = " + range.getLength());
        System.out.println("Число входит в интервал (true = да, false = нет.) = " + range.isInside(number));

        range.setFrom(12);
        range.setTo(16);

        System.out.println("Длина 2-ого диапазона = " + range.getLength());
        System.out.println("Число входит в интервал (true = да, false = нет.) =  " + range.isInside(number));

        Range range1 = new Range(from, to);

        Range [] intersectionRange = range.getIntersection(range1);
        System.out.println("Результат проверки пересечения = " + Arrays.toString(intersectionRange));

        Range[] union = range.getUnion(range1);
        System.out.println("Объединение двух интервалов = " + Arrays.toString(union));

        Range[] difference = range.getDifference(range1);
        System.out.println("Разница интервалов = " + Arrays.toString(difference));
    }
}