package ru.academits.shaduro.csv.main;


import ru.academits.shaduro.csv.ConvertCsv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                ConvertCsv.convertCsvToHtml(args[0], args[1]);

                System.out.println("Csv файл конвертирован.");
            } else {
                throw new FileNotFoundException("Укажите путь к одному файлу в формате Csv и путь к одному файлу в формате HTML.");
                //Todo если передали больше 2-х аргументов , то искл-е не актуально ?
            }
        } /*catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } */ catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}