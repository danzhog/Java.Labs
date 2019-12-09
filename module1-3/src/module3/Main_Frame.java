package laba_3C;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main_Frame extends JFrame{

    private static final long serialVersionUID = 1L;
    // ��������� � �������� �������� ���� ����������
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    // ������ ������������� ����������
    private Double[] coefficients;

    // ������ ����������� ���� ��� ������ ������
    // ��������� �� ��������� ����������, �. �. ����� � �� �����������
    // ������������ ���� ��� �� ���������� ��������� ������ � ����
    private JFileChooser fileChooser = null;

    // �������� ����
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem commaSeparatedValues;
    private JMenuItem searchValueMenuItem;
    private JMenuItem searchRangeMenuAction;

    private JMenuItem informationItem;

    // ���� ����� ��� ���������� �������� ����������
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;

    private Box hBoxResult;

    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    // ������������ ����� �������
    private GornerTableCell renderer = new GornerTableCell();

    // ������ ������ � ������������ ����������
    private GornerTable data;

    @SuppressWarnings("serial")
    public Main_Frame(Double[] coefficients)
    {
        super("������������� ���������� �� ������� �� ����� �������");

        // ��������� �� ���������� ���� ���������� ������������
        this.coefficients = coefficients;
        // ���������� ������� ����
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // �������������� ���� ���������� �� ������
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);

        //�������� ����������� � ��������� ��� � �������� ������
        Image img = kit.getImage("icon.gif");
        setIconImage(img);

        // ������� ����
        JMenuBar menuBar = new JMenuBar();
        // ���������� ���� � �������� �������� ���� ����������
        setJMenuBar(menuBar);
        // �������� � ���� ����� ���� "����"
        JMenu fileMenu = new JMenu("����");
        // �������� ��� � ������� ����
        menuBar.add(fileMenu);
        // ������� ����� ���� "�������"
        JMenu tableMenu = new JMenu("�������");
        // �������� ��� � ������� ����
        menuBar.add(tableMenu);

        JMenu infMenu = new JMenu("�������");
        menuBar.add(infMenu);
        // ������� ����� "��������" �� ���������� � ��������� ����
        Action saveToTextAction = new AbstractAction("��������� � ��������� ����") 	{
            public void actionPerformed(ActionEvent event)
            {
                if (fileChooser==null)
                {
                    // ���� ���������� ���� "������� ����" ��� �� �������,
                    // �� ������� ���
                    fileChooser = new JFileChooser();
                    // � ���������������� ������� �����������
                    fileChooser.setCurrentDirectory(new File("."));
                }
                // �������� ���������� ����
                if (fileChooser.showSaveDialog(Main_Frame.this) == JFileChooser.APPROVE_OPTION)
                    // ���� ��������� ��� ������ ��������, ��������� ������ �
                    // ��������� ����
                    saveToTextFile(fileChooser.getSelectedFile());
            }
        };

        // �������� ��������������� ����� ������� � ���� "����"
        saveToTextMenuItem = fileMenu.add(saveToTextAction);
        // �� ��������� ����� ���� �������� ����������� (������ ��� ���)
        saveToTextMenuItem.setEnabled(false);

        Action saveToCVSAction = new AbstractAction("��������� � CSV-����")
        {
            public void actionPerformed(ActionEvent arg0) {
                if (fileChooser == null){
                    // ���� ���������� ���� "������� ����" ��� �� �������,
                    // �� ������� ���
                    fileChooser = new JFileChooser();
                    // � ���������������� ������� �����������
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(Main_Frame.this) == JFileChooser.APPROVE_OPTION){
                    // ���� ��������� ��� ������ ��������, ��������� ������ �  ����
                    saveToCSVFile(fileChooser.getSelectedFile());
                }
            }
        };

        // �������� ��������������� ����� ������� � ���� "����"
        commaSeparatedValues = fileMenu.add(saveToCVSAction);
        // �� ��������� ����� ���� �������� ����������� (������ ��� ���)
        commaSeparatedValues.setEnabled(false);

        // ������� ����� "��������" �� ���������� � ��������� ����
        Action saveToGraphicsAction = new AbstractAction("��������� ������ ��� ���������� �������")
        {
            public void actionPerformed(ActionEvent event)
            {
                if (fileChooser==null)
                {
                    // ���� ���������� ���� "������� ����" ��� �� �������,
                    // �� ������� ���
                    fileChooser = new JFileChooser();
                    // � ���������������� ������� �����������
                    fileChooser.setCurrentDirectory(new File("."));
                }
                // �������� ���������� ����
                if (fileChooser.showSaveDialog(Main_Frame.this) == JFileChooser.APPROVE_OPTION);
                // ���� ��������� ������ ��������,
                // ��������� ������ � �������� ����
                saveToGraphicsFile(fileChooser.getSelectedFile());
            }
        };

        // �������� ��������������� ����� ������� � ���� "����"
        saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        // �� ��������� ����� ���� �������� ����������� (������ ��� ���)
        saveToGraphicsMenuItem.setEnabled(false);

        // ������� ����� �������� �� ������ �������� ����������
        Action searchValueAction = new AbstractAction("����� �������� ����������")
        {
            public void actionPerformed(ActionEvent event)
            {
                renderer.setSearch(null);
                // ��������� ������������ ������ ������� ������
                String value = JOptionPane.showInputDialog(Main_Frame.this,
                        "������� �������� ��� ������", "����� ��������", JOptionPane.QUESTION_MESSAGE);
                // ���������� ��������� �������� � �������� ������
                renderer.setSearch(value);
                // �������� �������
                getContentPane().repaint();
            }
        };

        // �������� �������� � ���� "�������"
        searchValueMenuItem = tableMenu.add(searchValueAction);
        // �� ��������� ����� ���� �������� ����������� (������ ��� ���)
        searchValueMenuItem.setEnabled(false);

        // ������� ����� �������� �� ������ �������� ����������
        Action searchRangeAction = new AbstractAction("����� �������� ���������� � ���������")
        {
            public void actionPerformed(ActionEvent event)
            {
                renderer.setSearch(null);

                // ��������� ������������ ������ ������� ������
                String value1 = JOptionPane.showInputDialog(Main_Frame.this,"������� �������� ������ �������",
                        "����� �������� �� ���������", JOptionPane.QUESTION_MESSAGE);

                String value2 = JOptionPane.showInputDialog(Main_Frame.this,"������� �������� ����� �������",
                        "����� �������� �� ���������", JOptionPane.QUESTION_MESSAGE);
                // ���������� ��������� �������� � �������� ������
                //renderer.setNeedle(value);
                renderer.setSearch(value1);
                // �������� �������
                getContentPane().repaint();
            }
        };

        // �������� �������� � ���� "�������"
        searchRangeMenuAction = tableMenu.add(searchRangeAction);
        // �� ��������� ����� ���� �������� ����������� (������ ��� ���)
        searchRangeMenuAction.setEnabled(false);

        // ������� ������ "� ���������"
        Action information = new AbstractAction("� ���������")
        {
            // ������ �������� �� ������� "� ���������"
            public void actionPerformed(ActionEvent event)
            {
                JOptionPane.showMessageDialog(Main_Frame.this,
                        "�����:\n ������ \n 5-� ������ ");
            }
        };

        informationItem = infMenu.add(information);

        JLabel labelForFrom = new JLabel("X ���������� �� ��������� ��:");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForTo = new JLabel("��:");
        textFieldTo = new JTextField("1.0", 10);
        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
        JLabel labelForStep = new JLabel("� �����:");
        textFieldStep = new JTextField("0.1", 10);
        textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());

        Box hboxRange = Box.createHorizontalBox();
        // ������ ��� ���������� ��� ����� "��������"
        hboxRange.setBorder(BorderFactory.createBevelBorder(1));
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForFrom);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldFrom);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForTo);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldTo);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForStep);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldStep);
        hboxRange.add(Box.createHorizontalGlue());

        // ���������� ���������������� ������ ������� ������ ����������
        // ������������, ����� ���  ���������� ������� ������ �� �������
        hboxRange.setPreferredSize(new Dimension(
                new Double(hboxRange.getMaximumSize().getWidth()).intValue(),
                new Double(hboxRange.getMinimumSize().getHeight()).intValue()*2));
        // ���������� ������� � ������� (��������) ����� ����������
        getContentPane().add(hboxRange, BorderLayout.NORTH);

        // ������� ������ "���������"
        JButton buttonCalc = new JButton("���������");
        // ������ �������� �� ������� "���������" � ��������� � ������
        buttonCalc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                try {
                    Double from = Double.parseDouble(textFieldFrom.getText());
                    Double to = Double.parseDouble(textFieldTo.getText());
                    Double step = Double.parseDouble(textFieldStep.getText());
                    data = new GornerTable(from, to, step, Main_Frame.this.coefficients);
                    JTable table = new JTable(data);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);
                    hBoxResult.removeAll();
                    hBoxResult.add(new JScrollPane(table));
                    getContentPane().validate();
                    saveToTextMenuItem.setEnabled(true);
                    saveToGraphicsMenuItem.setEnabled(true);
                    searchValueMenuItem.setEnabled(true);
                    searchRangeMenuAction.setEnabled(true);
                    commaSeparatedValues.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main_Frame.this,
                            "������ � ������� ������ ����� � ��������� ������", "��������� ������ �����",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("�������� ����");
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                searchRangeMenuAction.setEnabled(false);
                commaSeparatedValues.setEnabled(false);
                renderer.setSearch(null);
                renderer.setSearch(null);
                getContentPane().validate();
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setPreferredSize(new Dimension(new
                Double(hboxButtons.getMaximumSize().getWidth()).intValue(), new
                Double(hboxButtons.getMinimumSize().getHeight()).intValue()*2));
        getContentPane().add(hboxButtons, BorderLayout.SOUTH);
        hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(new JPanel());
        getContentPane().add(hBoxResult, BorderLayout.CENTER);
    }

    protected void saveToGraphicsFile(File selectedFile)
    {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));

            for (int i = 0; i<data.getRowCount(); i++)
            {
                out.writeDouble((Double)data.getValueAt(i,0));
                out.writeDouble((Double)data.getValueAt(i,1));
            }
            out.close();
        } catch (Exception e) {
        }
    }

    protected void saveToTextFile(File selectedFile)
    {
        try {
            PrintStream out = new PrintStream(selectedFile);
            out.println("Результаты табулирования многочлена по схеме Горнера");
            out.print("Многочлен: ");
            for (int i=0; i<coefficients.length; i++)
            {
                out.print(coefficients[i] + "*X^" +
                        (coefficients.length-i-1));
                if (i!=coefficients.length-1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Интервал от  " + data.getFrom() + " до " +
                    data.getTo() + " � ����� " + data.getStep());
            out.println("====================================================");
            for (int i = 0; i<data.getRowCount(); i++)
            {
                out.println("Значение в точке " + formatter.format(data.getValueAt(i,0))
                        + " равно " + formatter.format(data.getValueAt(i,1)));
            }
            out.close();
        } catch (FileNotFoundException e)
        {
        }
    }

    protected void saveToCSVFile(File selectedFile)
    {
			/*try{
				Csv.Writer writer = new Csv.Writer(selectedFile);
				writer.value("���������� ������������� ���������� �� ����� �������");
				writer.newLine();
				writer.value("�������� �� " + data.getFrom() + " �� " + data.getTo() + " � ����� " +  data.getStep());
				writer.newLine();
				writer.value("�������� X");
				writer.value("�������� ����������");
				writer.value("��������");
				writer.value("�������");
				writer.newLine();
				for (int i = 0; i < data.getRowCount(); i++){
					for(int k = 0; k < data. getColumnCount(); k++)
					{
						writer.value(formatter.format(data.getValueAt(i, k)));
						}
					writer.newLine();
					}
				writer.close();
				}catch(Exception e){

				}*/
    }


    public static void main(String[] args)
    {
        if (args.length==0)
        {
            System.out.println("���������� ������������ ���������, ��� �������� �� ������ �� ������ ������������!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[args.length];
        int i = 0;
        try {
            for (String arg: args) {
                coefficients[i++] = Double.parseDouble(arg);
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("������ �������������� ������ '" +
                    args[i] + "' � ����� ���� Double");
            System.exit(-2);
        }
        MainFrame frame = new MainFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}