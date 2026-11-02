package l02_records;

// 1. Record з компактним та неканонічним конструкторами
record Student(String name, int id, double averageGrade) {
    // Компактний канонічний конструктор (валідація)
    public Student {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути пустим");
        }
        if (averageGrade < 0 || averageGrade > 100) {
            throw new IllegalArgumentException("Бал має бути між 0 і 100");
        }
    }

    // Неканонічний конструктор (парсинг)
    public Student(String name, int id, String averageGradeStr) {
        this(name, id, Double.parseDouble(averageGradeStr));
    }

    // Додатковий метод обчислення
    public double getDiscountedGrade(double percentage) {
        return averageGrade - (averageGrade * percentage / 100.0);
    }
}

// 2. Record для курсів
record CourseRecord(String courseName, int courseId, double creditHours) {
    public CourseRecord {
        if (courseName.isEmpty()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою.");
        }
    }

    public CourseRecord(String courseName, int courseId, String creditHours) {
        this(courseName, courseId, Double.parseDouble(creditHours));
    }
}

public class RecordsDemo {
    public static void main(String[] args) {
        Student st1 = new Student("Олексій", 12345, 90.5);
        Student st2 = new Student("Марія", 67890, "95.0");

        System.out.println("Студент 1: " + st1.name() + ", Бал: " + st1.averageGrade());
        System.out.println("Знижений бал: " + st1.getDiscountedGrade(10));

        CourseRecord course = new CourseRecord("Java Programming", 101, "4.5");
        System.out.println("Курс: " + course.courseName() + ", Кредити: " + course.creditHours());
    }
}
