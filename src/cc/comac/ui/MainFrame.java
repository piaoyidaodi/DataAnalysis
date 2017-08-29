package cc.comac.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import cc.comac.ui.mainlayout.MainFrameCenterPane;
import cc.comac.ui.mainlayout.MainFrameWestPane;
import cc.comac.ui.menu.MainFrameMenu;
import cc.comac.ui.toolbar.MainFrameToolbar;
import cc.comac.util.Context;
import cc.comac.util.DeviceProperty;

public class MainFrame extends JFrame {
    
    private MainFrameMenu menu=null;
    private MainFrameToolbar toolbar=null;
    private JSplitPane mainSplitPane=null;
    private MainFrameCenterPane centerPane=null;
    private MainFrameWestPane westPane=null;

    public MainFrame() {
        menu = new MainFrameMenu(this);
        toolbar=new MainFrameToolbar(this);
        
        mainSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false);
        centerPane=new MainFrameCenterPane(mainSplitPane);
        westPane=new MainFrameWestPane(mainSplitPane);
        
        Context.getInstance().setMainFrame(this);
        Context.getInstance().setMainFrameMenu(menu);
        Context.getInstance().setMainFrameToolbar(toolbar);
        Context.getInstance().setMainFrameWestPane(westPane);
        Context.getInstance().setMainFrameCenterPane(centerPane);
        Context.getInstance().setMainPane(mainSplitPane);
        
        // MainSplitPane
        mainSplitPane.setLeftComponent(westPane);
//        mainSplitPane.setLeftComponent(tabbedPane);
        mainSplitPane.setRightComponent(centerPane);
        mainSplitPane.setDividerSize(5);
        mainSplitPane.setDividerLocation(150);
        
        add(menu);
        add(toolbar,BorderLayout.NORTH);
        add(mainSplitPane,BorderLayout.CENTER);
        
//        this.setLocation((int)(DeviceProperty.getDeviceWidth()*0.1), (int)(DeviceProperty.getDeviceHeight()*0.1));
//        this.setSize((int)(DeviceProperty.getDeviceWidth()*0.75), (int)(DeviceProperty.getDeviceHeight()*0.75));
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("MainFrame");
                frame.setLocation((int)(DeviceProperty.getDeviceWidth()*0.1), (int)(DeviceProperty.getDeviceHeight()*0.1));
                frame.setMinimumSize(new Dimension(920, 690));
                frame.setVisible(true);
            }
        });
    }

}
