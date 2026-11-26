import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Калькулятор");

        while (true) {
            System.out.print("\nВведіть перше число (q - вихід): ");
            String first = scanner.nextLine();

            if (first.equalsIgnoreCase("q")) {
                break;
            }

            try {
                System.out.print("Введіть друге число: ");
                String second = scanner.nextLine();

                System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
                String operation = scanner.nextLine();

                double a = Double.parseDouble(first.replace(',', '.'));
                double b = Double.parseDouble(second.replace(',', '.'));
                double result;

                switch (operation) {
                    case "+":
                        result = calculator.add(a, b);
                        break;
                    case "-":
                        result = calculator.subtract(a, b);
                        break;
                    case "*":
                        result = calculator.multiply(a, b);
                        break;
                    case "/":
                        result = calculator.divide(a, b);
                        break;
                    case "sqrt":
                        result = calculator.sqrt(a);
                        break;
                    default:
                        throw new InvalidInputException("Невідома операція: " + operation);
                }

                System.out.println("Результат: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Помилка: Некоректний формат числа. Введіть дійсне число.");
            } catch (ArithmeticException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Некоректні дані: " + e.getMessage());
            } finally {
                System.out.println("Обробку запиту завершено.");
            }
        }

        System.out.println("Вихід з програми");
        scanner.close();
    }
}