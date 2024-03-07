package ru.academits.shaduro.csv.main;

import ru.academits.shaduro.csv.CsvToHtmlConverter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Введено неверное количество аргументов - " + args.length + ". Количество аргументов должно быть 2.");
            System.out.println("Первым аргументом укажите путь к файлу \".csv\", вторым аргументом путь к файлу \".html\".");

            return;
        }

        try {
            CsvToHtmlConverter.convert(args[0], args[1]);
            System.out.println("Csv файл конвертирован.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}