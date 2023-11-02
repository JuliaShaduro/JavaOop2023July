package ru.academits.shaduro.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        try {
            ArrayList<String> linesList = getLinesList("input.");
            System.out.println(linesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 6, 44, 7, 8, 9, 62));

        getOddNumbersList(numbers);
        System.out.println(getNoRepeatNumbersList(numbers));
    }

    public static ArrayList<Integer> getNoRepeatNumbersList(ArrayList<Integer> numbers) {
        ArrayList<Integer> noRepeatNumbers = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!noRepeatNumbers.contains(number)) {
                noRepeatNumbers.add(number);
            }
        }

        return noRepeatNumbers;
    }

    public static void getOddNumbersList(ArrayList<Integer> numbers) {
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