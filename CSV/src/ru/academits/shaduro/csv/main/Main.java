package ru.academits.shaduro.csv.main;


import ru.academits.shaduro.csv.Csv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Csv.convertCsvToHtml("input.csv", "output.html");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}