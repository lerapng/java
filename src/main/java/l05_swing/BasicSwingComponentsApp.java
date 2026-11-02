package l05_swing;

import javax.swing.*;
import java.awt.*;

public class BasicSwingComponentsApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicSwingComponentsApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Демонстрація компонентів Swing та Layouts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());

        // Панель зверху (North)
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Інформація про студента:"));
        topPanel.add(new JTextField("Введіть ім'я", 15));
        frame.add(topPanel, BorderLayout.NORTH);

        // Центральна частина (Center)
        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Ласкаво просимо до навчальної системи!"));
        frame.add(centerPanel, BorderLayout.CENTER);

        // Кнопки по боках та знизу
        frame.add(new JButton("Захід"), BorderLayout.WEST);
        frame.add(new JButton("Схід"), BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JButton("Додати курс"));
        bottomPanel.add(new JButton("Редагувати курс"));
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
