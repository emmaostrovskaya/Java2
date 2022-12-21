package bsu.rfe.java.group9.lab1.kulashchyk.varB1;
// Импортируются классы, используемые в приложении

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class Main extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах

    private int activeMemoryCell = 0;

    private double memoryCells[] = new double[3];

    private JLabel memoryTextLabel1 = new JLabel("0");
    private JLabel memoryTextLabel2 = new JLabel("0");
    private JLabel memoryTextLabel3 = new JLabel("0");
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        //return (Math.sin(y) + y*y + Math.pow(Math.E, Math.cos(y))) * Math.pow(Math.log(z) + Math.sin(Math.PI*x*x),1.d/4);
    return (Math.pow(Math.pow(Math.log((1+z)),2)+Math.cos(Math.PI*y*y*y),1.0/4.0))
        (Math.pow(Math.cos(Math.pow(Math.E, x))+Math.sqrt(1/x)+Math.pow(Math.E, x*x),Math.sin(x)));
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        //return (Math.pow(y+x*x*x, 1.d/z))/Math.log(z);
        return (Math.tan(x*x)+Math.pow(y,1.d/2))/z*Math.log10(x+y);
    }
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    // Конструктор класса
    public Main() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
       // hboxVariables.add(Box.createHorizontalGlue());
       // hboxVariables.add(Box.createVerticalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        //hboxVariables.add(Box.createHorizontalGlue());
       // hboxVariables.add(Box.createVerticalGlue());
        hboxVariables.add(labelForZ);

        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 18);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        // Память
        JRadioButton rbMem1 = new JRadioButton("1");
        JRadioButton rbMem2 = new JRadioButton("2");
        JRadioButton rbMem3 = new JRadioButton("3");

        rbMem1.setSelected(true);

        ButtonGroup memButtonGroup = new ButtonGroup();
        memButtonGroup.add(rbMem1);
        memButtonGroup.add(rbMem2);
        memButtonGroup.add(rbMem3);

        rbMem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.activeMemoryCell = 0;
            }
        });

        rbMem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.activeMemoryCell = 1;
            }
        });

        rbMem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.activeMemoryCell = 2;
            }
        });


        JButton buttonMemoryPlus = new JButton("M+");

        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeCell = Main.this.activeMemoryCell;
                memoryCells[activeCell] += Double.parseDouble(textFieldResult.getText());
                updateMemoryLabels();
            }
        });

        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeCell = Main.this.activeMemoryCell;
                memoryCells[activeCell] = 0;
                updateMemoryLabels();

            }
        });

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth() * 480 / 1920;

        Box hboxMemory = Box.createHorizontalBox();
        //hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(rbMem1);
        hboxMemory.add(rbMem2);
        hboxMemory.add(rbMem3);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(Box.createVerticalGlue());
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(Box.createVerticalGlue());
        hboxMemory.add(buttonMemoryPlus);
        hboxMemory.add(buttonMemoryClear);
        //hboxMemory.add(Box.createHorizontalGlue());

        Box hboxMemoryLabels = Box.createHorizontalBox();
        hboxMemoryLabels.add(Box.createHorizontalGlue());
        hboxMemoryLabels.add(memoryTextLabel1);
        hboxMemoryLabels.add(Box.createHorizontalStrut(20));
        hboxMemoryLabels.add(memoryTextLabel2);
        hboxMemoryLabels.add(Box.createHorizontalStrut(20));
        hboxMemoryLabels.add(memoryTextLabel3);
        hboxMemoryLabels.add(Box.createHorizontalGlue());
        hboxMemoryLabels.setMaximumSize(new Dimension(width, 30));


        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemory);
        contentBox.add(hboxMemoryLabels);
        contentBox.add(Box.createVerticalGlue());

        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    private void updateMemoryLabels() {
        memoryTextLabel1.setText(String.format("%.4f", memoryCells[0]));
        memoryTextLabel2.setText(String.format("%.4f", memoryCells[1]));
        memoryTextLabel3.setText(String.format("%.4f", memoryCells[2]));
    }

    // Главный метод класса
    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}