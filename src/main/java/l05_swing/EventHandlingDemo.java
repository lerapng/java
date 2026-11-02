package l05_swing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class EventHandlingDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Обробка подій (Лямбди та Слухачі)");
            frame.setSize(350, 250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            // Кнопка з лямбда-виразом
            JButton button = new JButton("Натисніть мене (Lambda)");
            button.addActionListener(e ->
                    JOptionPane.showMessageDialog(frame, "Кнопку натиснуто через Lambda!")
            );

            // Список з ListSelectionListener
            String[] courses = {"Курс 1", "Курс 2", "Курс 3"};
            JList<String> courseList = new JList<>(courses);
            courseList.addListSelectionListener((ListSelectionEvent e) -> {
                if (!e.getValueIsAdjusting()) {
                    JOptionPane.showMessageDialog(frame, "Обрано: " + courseList.getSelectedValue());
                }
            });

            frame.add(button);
            frame.add(new JScrollPane(courseList));
            frame.setVisible(true);
        });
    }
}
