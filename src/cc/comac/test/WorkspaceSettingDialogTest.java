package cc.comac.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cc.comac.ui.dialog.WorkspaceSettingDialog;
import cc.comac.util.DeviceProperty;

public class WorkspaceSettingDialogTest {
    

    public static void main(String[] args) {
        
        String title="WorkspaceSettingDialogTest";
        JButton workspaceSettingDialogButton=new JButton("WorkspaceSettingDialogTest");

        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                JFrame frame=new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setTitle(title);
                frame.setVisible(true);
                
                workspaceSettingDialogButton.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        WorkspaceSettingDialog settingDialog=new WorkspaceSettingDialog(frame);
                        settingDialog.setLocation((int)DeviceProperty.getDeviceWidth()/3, (int)DeviceProperty.getDeviceHeight()/3);
                        settingDialog.setVisible(true);
                        
                    }
                });
                frame.add(workspaceSettingDialogButton);
                
                frame.pack();
                
            }
        });
    }

}
