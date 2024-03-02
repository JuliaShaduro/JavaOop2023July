package ru.academits.shaduro.lambda.main;

import ru.academits.shaduro.lambda.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Julia", 15),
                new Person("Lida", 25),
                new Person("Nik", 44),
                new Person("Misha", 32),
                new Person("Maria", 5),
                new Person("Nik", 5),
                new Person("Misha", 20)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println("Список уникальных имен: " + uniqueNames);
        System.out.println("Имена: " + String.join(", ", uniqueNames));

        List<Person> minors = persons.stream()
                .filter(person -> person.age() < 18)
                .toList();

        System.out.println("Список людей младше 18: " + minors);

//     Todo почему sum (minors.stream().mapToDouble(person-> person.age()).sum()) возвращает double = 0, а на average нет

        System.out.printf("Средний возраст людей младше 18 = %.2f", minors.stream()
                .mapToInt(Person::age)
                .average()
                .orElseThrow(() -> new NullPointerException("Люди с возрастом младше 18 лет отсутствуют. " +
                        "Среднее значение посчитать нельзя")));

        // Или возвращаем OptionalDouble Todo  average() ==  OptionalDouble averageAge1 = minors.stream().
        // Todo mapToInt(person-> person.age()).average();

        System.out.println();
        System.out.println("Средний возраст людей по персонам: " + persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingDouble(Person::age))));

        List<Person> middleAgePersons = persons.stream()
                .filter(person -> person.age() >= 20 & person.age() < 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .toList();

        System.out.println("Люди от 20 до 45 в порядке убывания возраста: " + middleAgePersons);
    }
}