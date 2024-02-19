package ru.academits.shaduro.csv.main;

import ru.academits.shaduro.csv.ConverterCsvToHtml;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Укажите путь к одному файлу в формате Csv и путь к одному файлу в формате HTML.");
        }

        try {
            ConverterCsvToHtml.convert(args[0], args[1]);
            System.out.println("Csv файл конвертирован.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}