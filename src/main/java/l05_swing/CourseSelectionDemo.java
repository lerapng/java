package l05_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CourseSelectionDemo implements ItemListener {
    private final JLabel selectedLabel;
    private final JLabel changedLabel;
    private final JCheckBox course1;
    private final JCheckBox course2;
    private final JCheckBox course3;

    public CourseSelectionDemo() {
        JFrame frame = new JFrame("Вибір курсів");
        frame.setLayout(new FlowLayout());
        frame.setSize(320, 160);

        course1 = new JCheckBox("Програмування");
        course2 = new JCheckBox("Алгоритми");
        course3 = new JCheckBox("Мережі");

        course1.addItemListener(this);
        course2.addItemListener(this);
        course3.addItemListener(this);

        selectedLabel = new JLabel("Обрані курси: ");
        changedLabel = new JLabel("");

        frame.add(course1);
        frame.add(course2);
        frame.add(course3);
        frame.add(changedLabel);
        frame.add(selectedLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        JCheckBox cb = (JCheckBox) ie.getItem();
        if (cb.isSelected()) {
            changedLabel.setText(cb.getText() + " обраний.");
        } else {
            changedLabel.setText(cb.getText() + " знятий.");
        }

        StringBuilder str = new StringBuilder();
        if (course1.isSelected()) str.append("Програмування ");
        if (course2.isSelected()) str.append("Алгоритми ");
        if (course3.isSelected()) str.append("Мережі ");

        selectedLabel.setText("Обрані курси: " + str);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseSelectionDemo::new);
    }
}
