package ru.academits.shaduro.csv.main;

import ru.academits.shaduro.csv.Csv;

public class Main {
    public static void main (String[] args) {
        if (args.length != 2) {
            System.out.println("Необходимо указать 2-а пути: к файлу csv и html.");
        } else {
            Csv csv = new Csv();
            csv.convertCSVtoHTML(args[0], args[1]);
        }
    }
}

