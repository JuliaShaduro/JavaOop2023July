package ru.academits.shaduro.range.program;

import ru.academits.shaduro.range.Range;

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

        System.out.println("Длина 1-ого диапазона  = " + range.getLength(range.getFrom(), range.getTo()));
        System.out.println("Число входит в интервал (true = да, false = нет.) = " + range.isInside(number));

        range.setFrom(3);
        range.setTo(5);

        System.out.println("Длина 2-ого диапазона = " + range.getLength(range.getFrom(), range.getTo()));
        System.out.println("Число входит в интервал (true = да, false = нет.) =  " + range.isInside(number));

        Range intersectionRange = range.getIntersection(from, to);

        if (intersectionRange == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.println("Результат проверки пересечения = "
                    + intersectionRange.getFrom() + " ; " + intersectionRange.getTo());
        }

        Range[] arrayunificationRange = range.getUnification(from, to);

        for (Range e : arrayunificationRange) {
            System.out.println("Объединение двух интервалов = " + e.getFrom() + " ; " + e.getTo());
        }

        Range[] arrayDifference = range.getDifference(from, to);

        if (arrayDifference == null) {
            System.out.println("Пустое множество.");
        } else {
            for (Range e : arrayDifference) {
                System.out.println("Разница интервалов = " + e.getFrom() + e.getTo());
            }
        }
    }
}