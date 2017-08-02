package cc.comac.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import cc.comac.ui.mainlayout.MainFrameCenterPane;
import cc.comac.ui.mainlayout.MainFrameWestPane;
import cc.comac.ui.menu.MainFrameMenu;
import cc.comac.ui.toolbar.MainFrameToolbar;

public class MainFrame extends JFrame {
    
    private JSplitPane mainSplitPane=null;

    public MainFrame() {
        MainFrameMenu menu = new MainFrameMenu(this);
        MainFrameToolbar toolbar=new MainFrameToolbar(this);
        
        mainSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false);
        MainFrameWestPane westPane=new MainFrameWestPane(mainSplitPane);
        MainFrameCenterPane centerPane=new MainFrameCenterPane(mainSplitPane);
        
//        JTabbedPane tabbedPane=new JTabbedPane();
//        tabbedPane.add(new JScrollPane(new JTree()));
        
        // MainSplitPane
        mainSplitPane.setLeftComponent(westPane);
//        mainSplitPane.setLeftComponent(tabbedPane);
        mainSplitPane.setRightComponent(centerPane);
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
