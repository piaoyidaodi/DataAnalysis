package cc.comac.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import cc.comac.ui.mainlayout.MainFrameWestPanel;
import cc.comac.ui.menu.MainFrameMenu;
import cc.comac.ui.toolbar.MainFrameToolbar;

public class MainFrame extends JFrame {
    
    private JSplitPane mainSplitPane=null;

    public MainFrame() {
        MainFrameMenu menu = new MainFrameMenu(this);
        MainFrameToolbar toolbar=new MainFrameToolbar(this);
        MainFrameWestPanel westPanel=new MainFrameWestPanel(this);
        MainFrameCenterPanel centerPanel=new MainFrameCenterPanel(this);
        
        // MainSplitPane
        mainSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        mainSplitPane.setLeftComponent(westPanel);
        mainSplitPane.setRightComponent(centerPanel);
        mainSplitPane.setDividerSize(5);
        
        add(menu);
        add(toolbar,BorderLayout.NORTH);
        add(mainSplitPane,BorderLayout.CENTER);
//        add(westPanel,BorderLayout.WEST);
//        add(dataTree,BorderLayout.WEST);
        
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
