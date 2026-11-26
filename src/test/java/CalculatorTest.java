import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void testAdd() throws InvalidInputException {
        assertEquals(5.0, calc.add(2, 3));
    }

    @Test
    void testSubtract() throws InvalidInputException {
        assertEquals(-1.0, calc.subtract(2, 3));
    }

    @Test
    void testMultiply() throws InvalidInputException {
        assertEquals(6.0, calc.multiply(2, 3));
    }

    @Test
    void testDivide() throws InvalidInputException {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void testSqrt() throws InvalidInputException {
        assertEquals(4.0, calc.sqrt(16));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    @Test
    void testSqrtNegative() {
        assertThrows(InvalidInputException.class, () -> calc.sqrt(-4));
    }

    @Test
    void testNaNInput() {
        assertThrows(InvalidInputException.class, () -> calc.add(Double.NaN, 1));
    }

    @Test
    void testInfinityInput() {
        assertThrows(InvalidInputException.class, () -> calc.multiply(Double.POSITIVE_INFINITY, 2));
    }

    @Test
    void testStringInsteadOfNumber() {
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("abc"));
    }
}
