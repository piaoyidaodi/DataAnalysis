package cc.comac.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cc.comac.util.DeviceProperty;

public class WorkspaceSettingDialog extends JDialog {

    private String WorkSpace;

    /**
     * This is a WorkspaceSettingDialog without a Frame owner.</br>
     * </br>
     * 
     * This Dialog will have only one "Next" Button to push the process of the
     * "First Use" this software.
     */
    
    // TODO The Components should be defined as "private" in the scope out of the Constructor for repeated code to be extract as a single function
        
    public WorkspaceSettingDialog() {
        super();
        this.setTitle("Set Work Directory");
        // Button "Next"
        JButton btnNext = new JButton("Next");

        // RadioButton of "Default UserWorkspace" and "SpecifyWorkspace"
        // Label,TextField and FileChooserButton
        // TODO The panel build ways of this place can be defined as variable parameter function, dynamic build panel
        JRadioButton rbtnDftWkSpace = new JRadioButton(
                "The Default UserWorkSpace of this WindowsPC is : " + DeviceProperty.getDefaultWkDir()+" ", true);
        JRadioButton rbtnSpeWkSpace = new JRadioButton("Custom WorkSpace:", false);
        JLabel lblSpeLabel = new JLabel("User Specify WorkSpace:",SwingConstants.RIGHT);
        JTextField txtfSpeWkDir = new JTextField(DeviceProperty.getDefaultWkDir());
        JButton fileChooserBtn=new JButton("...");

        // SettingPanel and ButtonPanel
        JPanel settingPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel pnl_speWorkDir = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();

        // Layout and Add Components to Panel
        buttonGroup.add(rbtnDftWkSpace);
        buttonGroup.add(rbtnSpeWkSpace);

        pnl_speWorkDir.setLayout(new GridLayout(1, 3));//GridBagLayout shall be used
        pnl_speWorkDir.add(lblSpeLabel);
        pnl_speWorkDir.add(txtfSpeWkDir);
        pnl_speWorkDir.add(fileChooserBtn);
        
        lblSpeLabel.setForeground(Color.GRAY);
        txtfSpeWkDir.setEditable(false);
        txtfSpeWkDir.setEnabled(false);
        fileChooserBtn.setEnabled(false);

        settingPanel.setLayout(new GridLayout(3, 1));
        settingPanel.add(rbtnDftWkSpace);
        settingPanel.add(rbtnSpeWkSpace);
        settingPanel.add(pnl_speWorkDir);
        btnPanel.add(btnNext);

        // RadioButtonActionListener
        rbtnDftWkSpace.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lblSpeLabel.setForeground(Color.GRAY);
                txtfSpeWkDir.setEnabled(false);
                txtfSpeWkDir.setEditable(false);
                fileChooserBtn.setEnabled(false);
            }
        });
        
        rbtnSpeWkSpace.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lblSpeLabel.setForeground(Color.BLACK);
                txtfSpeWkDir.setEnabled(true);
                txtfSpeWkDir.setEditable(true);
                fileChooserBtn.setEnabled(true);
            }
        });
        
        // fileChooserBtn ActionListener
        fileChooserBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO WorkSpace JFileChooser Dialog
                
            }
        });

        // NextActionListener
        btnNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });

        add(btnPanel, BorderLayout.SOUTH);
        add(settingPanel, BorderLayout.CENTER);
        pack();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JDialog dialog = new WorkspaceSettingDialog();
                dialog.setLocation((int)(DeviceProperty.getDeviceWidth()/3), (int)(DeviceProperty.getDeviceHeight()/3));
                dialog.setVisible(true);
            }
        });
    }

    String getWorkspace(){
        return WorkSpace;
    }
    
    private void setWorkPnlUnVisable(){
        
    }
}
