package ru.academits.shaduro.range.program;

import ru.academits.shaduro.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите 1-ое число интервала: ");
        double from = scanner.nextDouble();

        System.out.print("Введите 2-ое число интервала: ");
        double to = scanner.nextDouble();

        System.out.print("Введите число = ");
        double number = scanner.nextDouble();

        Range range1 = new Range(from, to);

        System.out.println("Длина 1-ого интервала = " + range1.getLength());
        System.out.println("Число входит в интервал (true = да, false = нет.) = " + range1.isInside(number));

        range1.setFrom(12);
        range1.setTo(23);

        System.out.println("Длина 2-ого интервала = " + range1.getLength());
        System.out.println("Число входит в интервал (true = да, false = нет.) = " + range1.isInside(number));

        Range range2 = new Range(from, to);

        System.out.println("Результат проверки пересечения = " + range1.getIntersection(range2));

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение двух интервалов = " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Разность интервалов = " + Arrays.toString(difference));
    }
}