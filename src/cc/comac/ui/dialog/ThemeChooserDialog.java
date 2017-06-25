package cc.comac.ui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import cc.comac.util.DeviceProperty;

public class ThemeChooserDialog extends JFrame {

    private JLabel promptLabel=null;
    private JComboBox<String> themeListComboBox=null;
    private JButton previousBtn=null;
    private JButton nextBtn=null;
    private JButton previewBtn=null;
    private JButton okBtn=null;
    private JButton cancelBtn=null;

    private JPanel chooseThemePanel=null;
    private JPanel btnPanel=null;

    private HashMap<String, String> theme;
    
    public ThemeChooserDialog() {
        super();
    }

    public ThemeChooserDialog(JFrame parent) {
        super();
        this.setTitle("Choose Theme");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        // Initial JComponent
        promptLabel = new JLabel("Choose Theme : ");
        themeListComboBox = new JComboBox<>();
        previousBtn = new JButton("Previous");
        nextBtn = new JButton("Next");
        previewBtn = new JButton("Preview");

        // Put JComponent to Panel
        chooseThemePanel = new JPanel(new GridLayout(1, 3));
        chooseThemePanel.add(promptLabel);
        chooseThemePanel.add(themeListComboBox);
        chooseThemePanel.add(previewBtn);

        btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(previousBtn);
        btnPanel.add(nextBtn);

        add(chooseThemePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        pack();

        // Initial
        theme = DeviceProperty.getJavaTheme();

        // Add Items to themeListComboBox
        for (String name : theme.keySet()) {
            themeListComboBox.addItem(name);
        }

        // Add ActionListener to ComboBox related previewBtn
        previewBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                
                String themeClass = theme.get(themeListComboBox.getItemAt(themeListComboBox.getSelectedIndex()));
                try {
                    UIManager.setLookAndFeel(themeClass);
                    SwingUtilities.updateComponentTreeUI(ThemeChooserDialog.this);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        // Add ActionListener to previousBtn
        previousBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                
                ThemeChooserDialog.this.setVisible(false);
                parent.setVisible(true);
                
            }
        });
        
        // Add ActionListener to nextBtn
        nextBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ThemeChooserDialog.this.dispose();
                parent.dispose();
                                            
            }
        });
        
    }
    
    public ThemeChooserDialog(JComponent parent){
        super();
        this.setTitle("Choose Theme");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                
        // Initial JComponent
        promptLabel = new JLabel("Choose Theme : ");
        themeListComboBox = new JComboBox<>();
        cancelBtn = new JButton("Cancel");
        okBtn = new JButton("OK");
        previewBtn = new JButton("Preview");

        // Put JComponent to Panel
        chooseThemePanel = new JPanel(new GridLayout(1, 3));
        chooseThemePanel.add(promptLabel);
        chooseThemePanel.add(themeListComboBox);
        chooseThemePanel.add(previewBtn);

        btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(cancelBtn);
        btnPanel.add(okBtn);

        add(chooseThemePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        pack();

        // Initial
        theme = DeviceProperty.getJavaTheme();

        // Add Items to themeListComboBox
        for (String name : theme.keySet()) {
            themeListComboBox.addItem(name);
        }

        // Add ActionListener to ComboBox related previewBtn
        previewBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                
                String themeClass = theme.get(themeListComboBox.getItemAt(themeListComboBox.getSelectedIndex()));
                try {
                    UIManager.setLookAndFeel(themeClass);
                    SwingUtilities.updateComponentTreeUI(ThemeChooserDialog.this);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        // Add ActionListener to cancelBtn
        cancelBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                
                ThemeChooserDialog.this.setVisible(false);                
            }
        });
        
        // Add ActionListener to okBtn
        okBtn.addActionListener(new ActionListener() {
            //TODO restore Theme
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ThemeChooserDialog.this.dispose();                                            
            }
        });
        
    }
    
    

    public static void main(String[] args) {

    }

}
