package ru.academits.shaduro.csv.main;

import ru.academits.shaduro.csv.Csv;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            Csv.convertCsvToHtml(args[0], args[1]);
        } else {
            System.out.println("Необходимо указать два пути: к файлу csv и html.");
        }
    }
}