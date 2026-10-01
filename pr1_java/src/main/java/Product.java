import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Category category;

    @Override
    public String toString() {
        String categoryName;
        if (category != null) {
            categoryName = category.getName();
        } else {
            categoryName = "Без категорії";
        }

        return "Товар{" +
                "id=" + id +
                ", назва='" + name + '\'' +
                ", ціна=" + price +
                ", опис='" + description + '\'' +
                ", категорія='" + categoryName + '\'' +
                '}';
    }
}
