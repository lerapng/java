import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printLargestAndSmallest(Transaction largest, Transaction smallest, String startDate, String endDate) {
        System.out.println("Витрати за період " + startDate + " - " + endDate + ":");
        if (largest == null || smallest == null) {
            System.out.println("Витрат за цей період немає");
            return;
        }
        System.out.println("Найбільша: " + largest.getDescription() + " " + largest.getAmount() + " (" + largest.getDate() + ")");
        System.out.println("Найменша: " + smallest.getDescription() + " " + smallest.getAmount() + " (" + smallest.getDate() + ")");
    }

    public static void printTopCategory(String category) {
        if (category == null) {
            System.out.println("Витрат немає");
        } else {
            System.out.println("Найбільше грошей витрачено на: " + category);
        }
    }

    public static void printCategoryChart(Map<String, Double> sums, double step) {
        System.out.println("Витрати по категоріях (1 * = " + step + " грн):");
        for (String category : sums.keySet()) {
            printChartLine(category, sums.get(category), step);
        }
    }

    public static void printMonthChart(Map<String, Double> sums, double step) {
        System.out.println("Витрати по місяцях (1 * = " + step + " грн):");
        for (String month : sums.keySet()) {
            printChartLine(month, sums.get(month), step);
        }
    }

    private static void printChartLine(String name, double sum, double step) {
        int count = (int) (sum / step);
        String line = name + ": " + sum + " ";
        for (int i = 0; i < count; i++) {
            line += "*";
        }
        System.out.println(line);
    }
}
