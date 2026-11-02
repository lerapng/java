package l05_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseRegistrationApp implements ActionListener {
    private final JLabel label;

    public CourseRegistrationApp() {
        JFrame frame = new JFrame("Реєстрація на курс");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 120);

        JButton registerButton = new JButton("Зареєструватися");
        registerButton.addActionListener(this);

        label = new JLabel("Натисніть кнопку для реєстрації.");

        frame.add(registerButton);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        label.setText("Ви успішно зареєстровані на курс!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseRegistrationApp::new);
    }
}
