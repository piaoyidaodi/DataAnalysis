package cc.comac.ui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cc.comac.util.DeviceProperty;

public class DefaultAboutDialog extends JFrame {
    
    private JLabel authorLabelName=null;
    private JLabel authorLabelValue=null;
    private JLabel blogLabelName=null;
    private JLabel blogLabelValue=null;
    private JLabel emailLabelName=null;
    private JLabel emailLabelValue=null;
    private JButton okButton=null;
    private JPanel informationPanel=null;
    private JPanel buttonPanel=null;
    
    private final int DEFAULT_WIDTH=(int)DeviceProperty.getDeviceWidth()/3;
    private final int DEFAULT_HEIGHT=(int)DeviceProperty.getDeviceHeight()/3;
    
    public DefaultAboutDialog() {
        // Initial Dialog
        this.setTitle("About...");
        this.setLocation(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Initial JComponent
        informationPanel=new JPanel();
        buttonPanel=new JPanel();
        authorLabelName=new JLabel("Author:",SwingConstants.CENTER);
        authorLabelValue=new JLabel("Drift",SwingConstants.CENTER);
        blogLabelName=new JLabel("Blog:",SwingConstants.CENTER);
        String aboutMeURL="https://github.com/piaoyidaodi";
        String aboutMe="<html><a href="+aboutMeURL+">piaoyidaodi</a></html>";
        blogLabelValue=new JLabel(aboutMe,SwingConstants.CENTER);
        emailLabelName=new JLabel("Email:",SwingConstants.CENTER);
        emailLabelValue=new JLabel("piaoyidaodi@gmail.com",SwingConstants.CENTER);

        okButton=new JButton("OK");
        
        // Add informationPanel Layout
        informationPanel.setLayout(new GridLayout(3,2));
        informationPanel.add(authorLabelName,0);
        informationPanel.add(authorLabelValue, 1);
        informationPanel.add(blogLabelName, 2);
        informationPanel.add(blogLabelValue, 3);
        informationPanel.add(emailLabelName, 4);
        informationPanel.add(emailLabelValue, 5);
        // Add buttonPanel Layout
//        GridBagLayout layout=new GridBagLayout();
//        buttonPanel.setLayout(layout);
//        GridBagConstraints constraints=new GridBagConstraints();
//        constraints.gridx=0;
//        constraints.gridy=0;
//        constraints.gridwidth=1;
//        constraints.gridheight=1;
//        constraints.weightx=100;
//        constraints.weighty=100;
//        constraints.anchor=GridBagConstraints.CENTER;
//        buttonPanel.add(okButton, constraints);
        buttonPanel.add(okButton);
        // ActionListener
        okButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultAboutDialog.this.setVisible(false);
            }
        });
        
        // Add Component to Frame
        this.add(informationPanel,BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
