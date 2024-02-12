package ru.academits.shaduro.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> linesList = getFileLines("input.csv");
            System.out.println("Список строк из файла: " + linesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 3, 6, 44, 7, 8, 9, 8, 62));

        System.out.println("Исходный список = " + numbers);

        deleteEvenNumbers(numbers);

        System.out.println("Список после удаления четных чисел = " + numbers);
        System.out.println("Список уникальных элементов = " + getNonRepeatingNumbersList(numbers));
    }

    public static ArrayList<Integer> getNonRepeatingNumbersList(ArrayList<Integer> numbers) {
        ArrayList<Integer> nonRepeatingNumbersList = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!nonRepeatingNumbersList.contains(number)) {
                nonRepeatingNumbersList.add(number);
            }
        }

        return nonRepeatingNumbersList;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);

                i--;
            }
        }
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        }

        return linesList;
    }
}