import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMAT);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                expenses.add(t);
            }
        }
        expenses.sort((a, b) -> Double.compare(a.getAmount(), b.getAmount()));
        List<Transaction> top = new ArrayList<>();
        for (int i = 0; i < expenses.size() && i < 10; i++) {
            top.add(expenses.get(i));
        }
        return top;
    }

    public static List<Transaction> findExpensesByPeriod(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMAT);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMAT);
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMAT);
            boolean inPeriod = !date.isBefore(start) && !date.isAfter(end);
            if (transaction.getAmount() < 0 && inPeriod) {
                result.add(transaction);
            }
        }
        return result;
    }

    public static Transaction findLargestExpense(List<Transaction> transactions, String startDate, String endDate) {
        List<Transaction> expenses = findExpensesByPeriod(transactions, startDate, endDate);
        Transaction largest = null;
        for (Transaction expense : expenses) {
            if (largest == null || expense.getAmount() < largest.getAmount()) {
                largest = expense;
            }
        }
        return largest;
    }

    public static Transaction findSmallestExpense(List<Transaction> transactions, String startDate, String endDate) {
        List<Transaction> expenses = findExpensesByPeriod(transactions, startDate, endDate);
        Transaction smallest = null;
        for (Transaction expense : expenses) {
            if (smallest == null || expense.getAmount() > smallest.getAmount()) {
                smallest = expense;
            }
        }
        return smallest;
    }

    public static Map<String, Double> sumExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                double current = result.getOrDefault(category, 0.0);
                result.put(category, current - transaction.getAmount());
            }
        }
        return result;
    }

    public static Map<String, Double> sumExpensesByMonth(List<Transaction> transactions) {
        Map<String, Double> result = new TreeMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMAT);
                String month = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                double current = result.getOrDefault(month, 0.0);
                result.put(month, current - transaction.getAmount());
            }
        }
        return result;
    }

    public static String findTopCategory(List<Transaction> transactions) {
        Map<String, Double> categories = sumExpensesByCategory(transactions);
        String topCategory = null;
        double max = 0;
        for (Map.Entry<String, Double> entry : categories.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }
        return topCategory;
    }
}
