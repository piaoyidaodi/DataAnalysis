package cc.comac.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cc.comac.ui.MainFrame;

public class MainFrameTest {
    public static void main(String[] agrs) {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                JFrame frame=new MainFrame();
                frame.setTitle("MainFrameTest");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }
}
