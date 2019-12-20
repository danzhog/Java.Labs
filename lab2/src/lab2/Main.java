package laba_2C;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.Scanner;
public class Main
{
    public static void main(String[]args) throws IOException {
       // Scanner in = new Scanner(System.in);
        //System.out.print("Input name: ");
        //String name = in.nextLine();
        //in.close();
       // System.out.print( name);


        Main_Frame frame = new Main_Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}