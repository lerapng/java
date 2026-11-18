import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, 0.001);
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        Assertions.assertEquals(2, TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023"));
        Assertions.assertEquals(1, TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023"));
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            transactions.add(new Transaction("01-01-2024", -i * 100.0, "Витрата" + i));
        }
        transactions.add(new Transaction("02-01-2024", 5000.0, "Зарплата"));

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, top.size());
        Assertions.assertEquals(-1200.0, top.get(0).getAmount(), 0.001);
        Assertions.assertEquals(-300.0, top.get(9).getAmount(), 0.001);
    }

    @Test
    public void testLargestAndSmallestExpense() {
        Transaction transaction1 = new Transaction("05-12-2023", -450.0, "Сільпо");
        Transaction transaction2 = new Transaction("07-12-2023", -120.0, "Аптека");
        Transaction transaction3 = new Transaction("14-12-2023", -3200.0, "Оренда");
        Transaction transaction4 = new Transaction("05-01-2024", -9000.0, "Подарунки");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4);

        Transaction largest = TransactionAnalyzer.findLargestExpense(transactions, "01-12-2023", "31-12-2023");
        Transaction smallest = TransactionAnalyzer.findSmallestExpense(transactions, "01-12-2023", "31-12-2023");

        Assertions.assertEquals("Оренда", largest.getDescription());
        Assertions.assertEquals("Аптека", smallest.getDescription());
    }

    @Test
    public void testSumExpensesByCategory() {
        Transaction transaction1 = new Transaction("05-12-2023", -450.0, "Сільпо");
        Transaction transaction2 = new Transaction("10-12-2023", -550.0, "Сільпо");
        Transaction transaction3 = new Transaction("12-12-2023", -120.0, "Аптека");
        Transaction transaction4 = new Transaction("15-12-2023", 8000.0, "Зарплата");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4);

        Map<String, Double> result = TransactionAnalyzer.sumExpensesByCategory(transactions);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1000.0, result.get("Сільпо"), 0.001);
        Assertions.assertEquals(120.0, result.get("Аптека"), 0.001);
        Assertions.assertEquals("Сільпо", TransactionAnalyzer.findTopCategory(transactions));
    }

    @Test
    public void testSumExpensesByMonth() {
        Transaction transaction1 = new Transaction("05-12-2023", -450.0, "Сільпо");
        Transaction transaction2 = new Transaction("20-12-2023", -550.0, "Подарунки");
        Transaction transaction3 = new Transaction("05-01-2024", -300.0, "Сільпо");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        Map<String, Double> result = TransactionAnalyzer.sumExpensesByMonth(transactions);

        Assertions.assertEquals(1000.0, result.get("2023-12"), 0.001);
        Assertions.assertEquals(300.0, result.get("2024-01"), 0.001);
    }
}
