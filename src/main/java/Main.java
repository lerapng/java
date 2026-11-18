import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println();

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        System.out.println();

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);
        System.out.println();

        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        Transaction largest = TransactionAnalyzer.findLargestExpense(transactions, startDate, endDate);
        Transaction smallest = TransactionAnalyzer.findSmallestExpense(transactions, startDate, endDate);
        TransactionReportGenerator.printLargestAndSmallest(largest, smallest, startDate, endDate);
        System.out.println();

        String topCategory = TransactionAnalyzer.findTopCategory(transactions);
        TransactionReportGenerator.printTopCategory(topCategory);
        System.out.println();

        Map<String, Double> byCategory = TransactionAnalyzer.sumExpensesByCategory(transactions);
        TransactionReportGenerator.printCategoryChart(byCategory, 1000);
        System.out.println();

        Map<String, Double> byMonth = TransactionAnalyzer.sumExpensesByMonth(transactions);
        TransactionReportGenerator.printMonthChart(byMonth, 1000);
    }
}
