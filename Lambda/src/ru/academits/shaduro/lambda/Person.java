package ru.academits.shaduro.lambda;

public record Person(String name, int age) {
    @Override
    public String toString() {
        return "name= " + name;
    }
}
