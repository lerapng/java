import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Order {
    private List<Product> products;
    private double totalPrice;
    private String status;

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }

    @Override
    public String toString() {
        String result = "Замовлення:\n";
        for (Product product : products) {
            result = result + product.toString() + "\n";
        }
        result = result + "Загальна вартість: " + totalPrice + "\n";
        result = result + "Статус: " + status;
        return result;
    }
}
