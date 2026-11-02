package l01_switch;

public class SwitchExtensionsDemo {

    public static void main(String[] args) {
        // 1. Стрілки та список констант case
        int credits = 30;
        String status = switch (credits) {
            case 30 -> "Відмінник";
            case 20 -> "Добре";
            case 10 -> "Задовільно";
            default -> "Потребує додаткового навчання";
        };
        System.out.println("Статус студента: " + status);

        // 2. Списки констант у діапазонах балів
        int examScore = 85;
        String category = switch (examScore) {
            case 90, 85, 80 -> "Відмінник";
            case 75, 70 -> "Добре";
            case 60, 65 -> "Задовільно";
            default -> "Не склав";
        };
        System.out.println("Категорія студента: " + category);

        // 3. Блоки коду з yield та складні вирази
        String courseType = "практика";
        boolean extraResources;

        String courseCategory = switch (courseType) {
            case "лекція", "семінар" -> {
                extraResources = false;
                yield "Теоретичний курс";
            }
            case "практика", "лабораторна" -> {
                extraResources = true;
                yield "Практичний курс";
            }
            case "курсовий проект" -> {
                extraResources = true;
                yield "Проектний курс";
            }
            default -> {
                extraResources = false;
                yield "Інше";
            }
        };

        System.out.println("Категорія курсу: " + courseCategory);
        if (extraResources) {
            System.out.println("Необхідна плата за додаткові ресурси.");
        }

        // 4. Обробка кодів помилок з викликом методів
        int errorCode = -1;
        boolean normalCompletion = false;

        String message = switch (errorCode) {
            case -1 -> getErrorMessage();
            case 0 -> {
                normalCompletion = true;
                yield "Завершено без помилок";
            }
            case 1 -> "Попередження: необхідна перевірка";
            default -> "Невідомий стан";
        };

        System.out.println("Повідомлення: " + message);
        if (normalCompletion) {
            System.out.println("Процес завершився успішно.");
        }
    }

    public static String getErrorMessage() {
        return "Помилка: неправильні дані.";
    }
}