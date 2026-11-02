package l03_sealed;

// 1. Запечатаний абстрактний клас
sealed abstract class Course permits MathCourse, PhysicsCourse {
    public abstract void courseDetails();
}

final class MathCourse extends Course {
    @Override
    public void courseDetails() {
        System.out.println("Це курс з математики.");
    }
}

final class PhysicsCourse extends Course {
    @Override
    public void courseDetails() {
        System.out.println("Це курс з фізики.");
    }
}

// 2. Запечатаний інтерфейс
sealed interface CourseIF permits AdvancedMath, AdvancedPhysics {
    String courseName();
}

final class AdvancedMath implements CourseIF {
    @Override
    public String courseName() {
        return "Поглиблена математика";
    }
}

final class AdvancedPhysics implements CourseIF {
    @Override
    public String courseName() {
        return "Поглиблена фізика";
    }
}

public class SealedHierarchyDemo {
    public static void main(String[] args) {
        Course c1 = new MathCourse();
        c1.courseDetails();

        CourseIF c2 = new AdvancedPhysics();
        System.out.println("Інтерфейс: " + c2.courseName());
    }
}
