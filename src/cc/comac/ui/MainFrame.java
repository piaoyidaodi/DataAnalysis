package cc.comac.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cc.comac.ui.menu.MainFrameMenu;
import cc.comac.ui.toolbar.MainFrameToolbar;

public class MainFrame extends JFrame {

    public MainFrame() {
        MainFrameMenu menu = new MainFrameMenu(this);
        MainFrameToolbar toolbar=new MainFrameToolbar(this);

        add(menu);
        add(toolbar);
        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("MainFrame");
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setVisible(true);
                

            }
        });
    }

}
