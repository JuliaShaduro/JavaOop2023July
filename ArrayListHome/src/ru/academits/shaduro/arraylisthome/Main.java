package ru.academits.shaduro.arraylisthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
      //  System.out.println(lineArrayList(args[0]));

        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println(oddNumberArrayList(numbers));
        System.out.println(noRepeatElementsArrayList(numbers));
    }

    public static ArrayList<Integer> noRepeatElementsArrayList (ArrayList<Integer> numbers) {
        if (numbers.size() == 0) { //Todo добавила обработку искл. задала вопрос надо ли
            throw new IndexOutOfBoundsException("Индекс = " + "}");
        }

        ArrayList<Integer> noRepeatElements = new ArrayList<>();

        for (Integer num : numbers) {
            if (!noRepeatElements.contains(num)) {
                noRepeatElements.add(num);
            }
        }

        return noRepeatElements;
    }

    public static ArrayList<Integer> oddNumberArrayList (ArrayList<Integer> numbers) {
        if (numbers.size() == 0) {
            throw new IndexOutOfBoundsException("Индекс = " + "}");
        }

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);

                i--;
            }
        }

        return numbers;
    }

    public static ArrayList<String> lineArrayList (String inputFilePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            ArrayList<String> names = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                names.add(line);
            }

            return names;

        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }

        return null;
    }
}