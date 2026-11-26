public class Calculator {
    private void checkNumber(double x) throws InvalidInputException {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new InvalidInputException("Некоректне число");
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        checkNumber(a);
        checkNumber(b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        checkNumber(a);
        checkNumber(b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        checkNumber(a);
        checkNumber(b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        checkNumber(a);
        checkNumber(b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        checkNumber(a);
        if (a < 0) {
            throw new InvalidInputException("Не можна добути корінь з від'ємного числа");
        }
        return Math.sqrt(a);
    }
}
