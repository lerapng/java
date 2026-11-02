package l05_swing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class CourseListDemo implements ListSelectionListener {
    private final JList<String> courseList;
    private final JLabel selectedLabel;

    public CourseListDemo() {
        JFrame frame = new JFrame("Вибір курсів");
        frame.setLayout(new FlowLayout());
        frame.setSize(280, 160);

        String[] courses = { "Програмування", "Алгоритми", "Мережі", "Бази даних", "Штучний інтелект" };
        courseList = new JList<>(courses);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.setPreferredSize(new Dimension(120, 90));

        selectedLabel = new JLabel("Виберіть курс");

        courseList.addListSelectionListener(this);

        frame.add(scrollPane);
        frame.add(selectedLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedCourse = courseList.getSelectedValue();
            selectedLabel.setText("Вибрано курс: " + selectedCourse);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseListDemo::new);
    }
}
