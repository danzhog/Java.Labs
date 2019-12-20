package laba_3C;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import  java.io.IOException;
public class Main_app {
    public static void main(String[] args)
    {
        args=new String[2];
        args[0]="1"; args[1]="2";// args[2]="1";




        if (args.length==0) { System.out.println("Не задано ни одного коэффициента!");
            System.exit(-1);
        }
        Double[] coeff = new Double[args.length];
        int i = 0;
        try {
            for (String arg: args) {
                coeff[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка преобразования строки '" + args[i] + "' в число типа Double");
            System.exit(-2);
        }

        MainFrame frame=new MainFrame(coeff);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}