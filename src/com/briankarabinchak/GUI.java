package com.briankarabinchak;

import javax.swing.*;
import java.awt.*;

class SplashWindow {
    public static void initGUI() {
        //Create Initial Frame
        JFrame frame = new JFrame("Dice Roll Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout());

        //Making a menubar
        JMenuBar mb = new JMenuBar();
        JMenu filem = new JMenu("File");
        JMenu helpm = new JMenu("Help");
        mb.add(filem);
        mb.add(helpm);
        JMenuItem exitApp = new JMenuItem("Exit");
        JMenuItem aboutApp = new JMenuItem("About");
        filem.add(exitApp);
        helpm.add(aboutApp);

        //Creating Components
        JButton button1 = new JButton("Button1");
        JButton button2 = new JButton("Button2");

        //Adding components to frame and turning it on
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.setVisible(true);
    }
}

public class GUI {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SplashWindow f = new SplashWindow();
                    f.initGUI();
                } catch (Exception e) {
                    System.out.println("Error running GUI");
                    e.printStackTrace();
                }
            }
        });
    }
}
