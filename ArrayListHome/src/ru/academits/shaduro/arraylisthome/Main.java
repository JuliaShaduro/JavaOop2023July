package ru.academits.shaduro.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> linesList = getLinesList("input.csv");
            System.out.println(linesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 3, 6, 44, 7, 8, 9, 8, 62));

        System.out.println("Исходный список = " + numbers);

        removingEvenNumbers(numbers);

        System.out.println("Список после удаления четных чисел = " + numbers);
        System.out.println("Список уникальных элементов = " + getNonRepeatingNumbersList(numbers));
    }

    public static ArrayList<Integer> getNonRepeatingNumbersList(ArrayList<Integer> numbers) {
        ArrayList<Integer> NonRepeating = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!NonRepeating.contains(number)) {
                NonRepeating.add(number);
            }
        }

        return NonRepeating;
    }

    public static void removingEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);

                i--;
            }
        }
    }

    public static ArrayList<String> getLinesList(String filePath) throws IOException {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while (null != (line = bufferedReader.readLine())) {
                linesList.add(line);
            }
        }

        return linesList;
    }
}