import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> catalog = new ArrayList<>();
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nМеню");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Пошук товарів (назва/категорія)");
            System.out.println("6 - Зробити замовлення");
            System.out.println("7 - Переглянути історію замовлень");
            System.out.println("0 - Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nКаталог товарів");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Введіть ID товару для додавання до кошика: ");
                    int addId = scanner.nextInt();
                    Product foundToAdd = null;
                    for (Product p : catalog) {
                        if (p.getId() == addId) {
                            foundToAdd = p;
                            break;
                        }
                    }
                    if (foundToAdd != null) {
                        cart.addProduct(foundToAdd);
                        System.out.println("Товар " + foundToAdd.getName() + " додано до кошика");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній!");
                        break;
                    }
                    System.out.print("Введіть ID товару для видалення з кошика: ");
                    int removeId = scanner.nextInt();
                    Product foundToRemove = null;
                    for (Product p : cart.getProducts()) {
                        if (p.getId() == removeId) {
                            foundToRemove = p;
                            break;
                        }
                    }
                    if (foundToRemove != null) {
                        cart.removeProduct(foundToRemove);
                        System.out.println("Товар видалено з кошика");
                    } else {
                        System.out.println("Товар з таким ID не знайдено в кошику.");
                    }
                    break;

                case 4:
                    System.out.println("\nВаша корзина");
                    System.out.println(cart);
                    break;

                case 5:
                    System.out.print("Введіть пошуковий запит (назва або категорія): ");
                    String query = scanner.nextLine().toLowerCase();
                    boolean foundAny = false;
                    System.out.println("\nРезультати пошуку:");
                    for (Product p : catalog) {
                        if (p.getName().toLowerCase().contains(query) ||
                                (p.getCategory() != null && p.getCategory().getName().toLowerCase().contains(query))) {
                            System.out.println(p);
                            foundAny = true;
                        }
                    }
                    if (!foundAny) {
                        System.out.println("Нічого не знайдено.");
                    }
                    break;

                case 6:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення успішно оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 7:
                    System.out.println("\nІсторія замовлень");
                    if (orderHistory.isEmpty()) {
                        System.out.println("Ви ще не зробили жодного замовлення.");
                    } else {
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("№" + (i + 1) + " " + orderHistory.get(i));
                        }
                    }
                    break;

                case 0:
                    System.out.println("Вихід");
                    return;

                default:
                    System.out.println("Невідома опція, спробуйте ще раз");
                    break;
            }
        }
    }
}
