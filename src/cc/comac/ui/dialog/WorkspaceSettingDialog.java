package cc.comac.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cc.comac.util.Context;
import cc.comac.util.DeviceProperty;

public class WorkspaceSettingDialog extends JFrame {

    private String workSpace;

    /**
     * This is a WorkspaceSettingDialog without a Frame owner.</br>
     * </br>
     * 
     * This Dialog will have only one "Next" Button to push the process of the
     * "First Use" this software.
     */

    // TODO The JComponents should be defined as "private" in the scope out of
    // the Constructor for repeated code to be extract as a single function

    public WorkspaceSettingDialog(JFrame parent) {
        super();
        this.setTitle("Set Work Directory");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
//        Context.getInstance().setWorkSpace(DeviceProperty.getDefaultWkDir());
        
        // Button "Next"
        JButton nextBtn = new JButton("Next");

        // RadioButton of "Default UserWorkspace" and "SpecifyWorkspace"
        // Label,TextField and FileChooserButton
        // TODO The panel build ways of this place can be defined as variable
        // parameter function, dynamic build panel
        JRadioButton defaultWkSpaceRadioBtn = new JRadioButton(
                "The Default UserWorkSpace of this WindowsPC is : " + DeviceProperty.getDefaultWkDir() + " ", true);
        JRadioButton specifyWkSpaceRadioBtn = new JRadioButton("Custom WorkSpace:", false);

        JLabel specifyWkSpaceLabel = new JLabel("User Specify WorkSpace:", SwingConstants.RIGHT);
        JTextField specifyWkSpaceTxtField = new JTextField(DeviceProperty.getDefaultWkDir());
        JButton fileChooserBtn = new JButton("...");

        // SettingPanel and ButtonPanel
        JPanel settingPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel specifyWkSpacePanel = new JPanel();
        ButtonGroup wkSpaceButtonGroup = new ButtonGroup();

        // Layout and Add Components to Panel
        wkSpaceButtonGroup.add(defaultWkSpaceRadioBtn);
        wkSpaceButtonGroup.add(specifyWkSpaceRadioBtn);

        specifyWkSpacePanel.setLayout(new GridLayout(1, 3));// GridBagLayout
                                                            // shall be used
        specifyWkSpacePanel.add(specifyWkSpaceLabel);
        specifyWkSpacePanel.add(specifyWkSpaceTxtField);
        specifyWkSpacePanel.add(fileChooserBtn);

        // Initial UI
        specifyWkSpaceLabel.setForeground(Color.GRAY);
        specifyWkSpaceTxtField.setEditable(false);
        specifyWkSpaceTxtField.setEnabled(false);
        fileChooserBtn.setEnabled(false);

        settingPanel.setLayout(new GridLayout(3, 1));
        settingPanel.add(defaultWkSpaceRadioBtn);
        settingPanel.add(specifyWkSpaceRadioBtn);
        settingPanel.add(specifyWkSpacePanel);
        btnPanel.add(nextBtn);

        // RadioButtonActionListener
        defaultWkSpaceRadioBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                specifyWkSpaceLabel.setForeground(Color.GRAY);
                specifyWkSpaceTxtField.setEnabled(false);
                specifyWkSpaceTxtField.setEditable(false);
                fileChooserBtn.setEnabled(false);
            }
        });

        specifyWkSpaceRadioBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                specifyWkSpaceLabel.setForeground(Color.BLUE);
                specifyWkSpaceTxtField.setEnabled(true);
                specifyWkSpaceTxtField.setEditable(true);
                fileChooserBtn.setEnabled(true);
            }
        });

        // fileChooserBtn ActionListener
        fileChooserBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO WorkSpace JFileChooser Dialog
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setCurrentDirectory(new File(specifyWkSpaceTxtField.getText()));
                fileChooserDialog.setFileSelectionMode(FileChooserDialog.DIRECTORIES_ONLY);
                
                int result=fileChooserDialog.showOpenDialog(WorkspaceSettingDialog.this);
                
                if (result==JFileChooser.APPROVE_OPTION) {
                    workSpace=fileChooserDialog.getSelectedFile().getAbsolutePath();
                    specifyWkSpaceTxtField.setText(workSpace);
                    
                }
            }
        });

        // NextBtn ActionListener
        nextBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                EventQueue.invokeLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        JFrame dialog = new ThemeChooserDialog(WorkspaceSettingDialog.this);
//                        dialog.setLocation((int) (DeviceProperty.getDeviceWidth() / 3),
//                                (int) (DeviceProperty.getDeviceHeight() / 3));
//                        dialog.setVisible(true);
//                    }
//                });
                // TODO Choose the Theme of the SoftWare, Write the WorkSpace to Property.
                
                JFrame dialog = new ThemeChooserDialog(WorkspaceSettingDialog.this);
                dialog.setLocation((int) (DeviceProperty.getDeviceWidth() / 3),
                        (int) (DeviceProperty.getDeviceHeight() / 3));
                dialog.setVisible(true);
                setVisible(false);
                
                if (defaultWkSpaceRadioBtn.isSelected()) {
                    Context.getInstance().setTarget(DeviceProperty.getDefaultWkDir());
                } else {
                    Context.getInstance().setTarget(specifyWkSpaceTxtField.getText());
                }
                
                System.out.println(Context.getInstance().getTarget());
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
                JFrame dialog = new WorkspaceSettingDialog(null);
                dialog.setLocation((int) (DeviceProperty.getDeviceWidth() / 3),
                        (int) (DeviceProperty.getDeviceHeight() / 3));
                dialog.setVisible(true);
            }
        });
    }

    String getWorkspace() {
        return workSpace;
    }

}
