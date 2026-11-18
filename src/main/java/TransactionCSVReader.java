import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream stream;
            if (path.startsWith("http")) {
                stream = new URL(path).openStream();
            } else {
                stream = new FileInputStream(path);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
        return lines;
    }

    public static List<Transaction> parseLines(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for (String rawLine : lines) {
            String line = rawLine.replace("\uFEFF", "").trim();
            if (line.isEmpty() || line.startsWith("Дата")) {
                continue;
            }
            String[] values = line.split(",", 3);
            if (values.length < 3) {
                System.out.println("Пропущено рядок: " + line);
                continue;
            }
            try {
                double amount = Double.parseDouble(values[1].trim());
                transactions.add(new Transaction(values[0].trim(), amount, values[2].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено рядок: " + line);
            }
        }
        return transactions;
    }

    public static List<Transaction> readTransactions(String path) {
        List<String> lines = readLines(path);
        return parseLines(lines);
    }
}
