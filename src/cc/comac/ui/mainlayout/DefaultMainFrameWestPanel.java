package cc.comac.ui.mainlayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultMainFrameWestPanel extends JPanel {
    
    private JPanel treePanel=null;

    public DefaultMainFrameWestPanel() {
        super();
    }

    public DefaultMainFrameWestPanel(JFrame mainFrame) {
        super();
        
        treePanel=this.addChildPanel(this,"Label",new MainFrameLabelTree(this));

    }

    private JPanel addChildPanel(JPanel parent,String labelName,JComponent childComponent) {
        JLabel childPanelNameLabel=new JLabel(labelName);
        
        JPanel childPanel=new JPanel();
        // add layout of align to left
        JPanel childNameLabelPanel=new JPanel();
        
        return childPanel;
    }
}
