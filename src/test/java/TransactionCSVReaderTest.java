import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testParseLines() {
        List<String> lines = Arrays.asList(
                "Дата,Сума,Категорія",
                "05-12-2023,-450,Сільпо",
                "10-12-2023,8000,Зарплата",
                "",
                "рядок без даних"
        );

        List<Transaction> result = TransactionCSVReader.parseLines(lines);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("05-12-2023", result.get(0).getDate());
        Assertions.assertEquals(-450.0, result.get(0).getAmount(), 0.001);
        Assertions.assertEquals("Сільпо", result.get(0).getDescription());
        Assertions.assertEquals(8000.0, result.get(1).getAmount(), 0.001);
        Assertions.assertEquals("Зарплата", result.get(1).getDescription());
    }
}
