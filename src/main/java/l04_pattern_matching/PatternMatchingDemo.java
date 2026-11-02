package l04_pattern_matching;

public class PatternMatchingDemo {

    public static void main(String[] args) {
        // 1. Pattern Matching для instanceof
        Object obj = "Hello Java";

        if (obj instanceof String s) {
            System.out.println("Рядок у верхньому регістрі: " + s.toUpperCase());
        }

        Object numObj = 5;
        if (numObj instanceof Integer i && (i > 0 && i < 10)) {
            System.out.println("Це ціле число між 1 і 10: " + i);
        }

        // 2. Pattern Matching у switch
        printObjectType("Java");
        printObjectType(100);
        printObjectType(null);
        printObjectType(3.14);
    }

    public static void printObjectType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Це ціле число: " + i);
            case String s -> System.out.println("Це рядок: " + s);
            case null -> System.out.println("Немає значення (null)");
            default -> System.out.println("Інший тип: " + obj.getClass().getSimpleName());
        }
    }
}
