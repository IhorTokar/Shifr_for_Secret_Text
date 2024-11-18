package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainApp extends JFrame {
    private JPanel mainPanel;
    private JButton button1, button2, button3;
    private JPanel contentPanel;

    public MainApp() {
        setTitle("Cezar Code GUI Example");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Панель для кнопок
        mainPanel = new JPanel();
        button1 = new JButton("Шифр Цезаря");
        button2 = new JButton("Шифр Візенера");
        button3 = new JButton("Очистити");

        // Додаємо кнопки на панель
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);

        // Панель для динамічного вмісту
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Додаємо панелі на головний контейнер
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // Додаємо обробники подій для кнопок
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOption1();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOption2();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearContentPanel();
            }
        });
    }

    // Метод для першої опції (Шифр Цезаря) з кнопками імпорт/експорт
    private void showOption1() {
        clearContentPanel();

        JLabel textLabel = new JLabel("Введіть текст:");
        JTextField textField = new JTextField(15);
        JLabel numberLabel = new JLabel("Введіть ключ шифрування (число):");
        JTextField numberField = new JTextField(5);
        JLabel resultLabel = new JLabel("Результат:");
        JTextField resultField = new JTextField(15);
        resultField.setEditable(false); // Поле тільки для читання

        // Обмежуємо розмір текстових полів
        textField.setMaximumSize(new Dimension(600, 30));
        numberField.setMaximumSize(new Dimension(100, 30));
        resultField.setMaximumSize(new Dimension(500, 30));

        JButton encryptButton = new JButton("Шифрувати");
        JButton decryptButton = new JButton("Дешифрувати");
        JButton importButton = new JButton("Імпорт");
        JButton exportButton = new JButton("Експорт");

        // Панель для вхідних і вихідних полів
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

        // Додаємо компоненти на панель вмісту
        inputPanel.add(textLabel);
        inputPanel.add(textField);
        inputPanel.add(numberLabel);
        inputPanel.add(numberField);
        inputPanel.add(resultLabel);
        inputPanel.add(resultField);

        contentPanel.add(inputPanel);
        contentPanel.add(encryptButton);
        contentPanel.add(decryptButton);
        contentPanel.add(importButton);
        contentPanel.add(exportButton);

        // Обробник подій для кнопки "Шифрувати"
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = textField.getText();
                    int key = Integer.parseInt(numberField.getText());
                    Cezar_CodeShifr shifrator = new Cezar_CodeShifr(text, key);
                    String encryptedText = shifrator.Chezar_shifrator(text, key);
                    resultField.setText(encryptedText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPanel, "Будь ласка, введіть коректне число!");
                }
            }
        });

        // Обробник подій для кнопки "Дешифрувати"
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = textField.getText();
                    int key = Integer.parseInt(numberField.getText());
                    Cezar_CodeShifr shifrator = new Cezar_CodeShifr(text, key);
                    String decryptedText = shifrator.Chezar_deshifrator(text, key);
                    resultField.setText(decryptedText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPanel, "Будь ласка, введіть коректне число!");
                }
            }
        });

        // Обробник подій для кнопки "Імпорт"
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Cezar_CodeShifr shifrator = new Cezar_CodeShifr("", 0);
                    shifrator.file_export(selectedFile.getAbsolutePath());
                    textField.setText(shifrator.getText());
                    numberField.setText(String.valueOf(shifrator.getLiteral_out()));
                }
            }
        });

        // Обробник подій для кнопки "Експорт"
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String text = textField.getText();
                    String key = numberField.getText();
                    Cezar_CodeShifr shifrator = new Cezar_CodeShifr(text, Integer.parseInt(key));
                    shifrator.fileImport(selectedFile.getAbsolutePath(), text, Integer.parseInt(key));
                }
            }
        });

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Метод для другої опції (Шифр Віженера)
    private void showOption2() {
        clearContentPanel();

        JLabel textLabel1 = new JLabel("Введіть текст:");
        JTextField textField1 = new JTextField(15);
        JLabel codeLabel = new JLabel("Введіть ключове слово:");
        JTextField codeField = new JTextField(15);
        JLabel resultLabel = new JLabel("Результат:");
        JTextField resultField = new JTextField(15);
        resultField.setEditable(false); // Поле тільки для читання

        // Обмежуємо розмір текстових полів
        textField1.setMaximumSize(new Dimension(600, 30));
        codeField.setMaximumSize(new Dimension(500, 30));
        resultField.setMaximumSize(new Dimension(500, 30));

        JButton encryptButton = new JButton("Шифрувати");
        JButton decryptButton = new JButton("Дешифрувати");
        JButton importButton = new JButton("Імпорт");
        JButton exportButton = new JButton("Експорт");

        // Панель для вхідних і вихідних полів
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));

        inputPanel.add(textLabel1);
        inputPanel.add(textField1);
        inputPanel.add(codeLabel);
        inputPanel.add(codeField);
        inputPanel.add(resultLabel);
        inputPanel.add(resultField);

        contentPanel.add(inputPanel);
        contentPanel.add(encryptButton);
        contentPanel.add(decryptButton);
        contentPanel.add(importButton);
        contentPanel.add(exportButton);

        // Обробник подій для кнопки "Шифрувати"
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = textField1.getText();
                    String code = codeField.getText();
                    Wizeners_CodeShifr wizenersCodeShifr = new Wizeners_CodeShifr(text, code);
                    String encryptedText = wizenersCodeShifr.Wizener_shifrator(text, code);
                    resultField.setText(encryptedText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPanel, "Будь ласка, введіть коректний текст або ключ!");
                }
            }
        });

        // Обробник подій для кнопки "Дешифрувати"
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = textField1.getText();
                    String code = codeField.getText();
                    Wizeners_CodeShifr wizenersCodeShifr = new Wizeners_CodeShifr(text, code);
                    String decryptedText = wizenersCodeShifr.Wizener_deshifrator(text, code);
                    resultField.setText(decryptedText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPanel, "Будь ласка, введіть коректний текст або ключ!");
                }
            }
        });

        // Обробник подій для кнопки "Імпорт"
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Wizeners_CodeShifr shifrator = new Wizeners_CodeShifr("", "");
                    shifrator.file_export(selectedFile.getAbsolutePath());
                    textField1.setText(shifrator.getText());
                    codeField.setText(shifrator.getCode_text());
                }
            }
        });

        // Обробник подій для кнопки "Експорт"
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String text = textField1.getText();
                    String code = codeField.getText();
                    Wizeners_CodeShifr shifrator = new Wizeners_CodeShifr(text, code);
                    shifrator.fileImport(selectedFile.getAbsolutePath(), text, code);
                }
            }
        });

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Метод для очищення панелі вмісту
    private void clearContentPanel() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}
