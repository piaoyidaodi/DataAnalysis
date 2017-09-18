package cc.comac.ui.dialog;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cc.comac.controller.DrawPanelController;
import cc.comac.ui.mainlayout.DataDrawPanel;

public class SettingPanelDialog extends JDialog {
    
    private DataDrawPanel panel;
    private DrawPanelController controller;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    
    private DataPropertyPanel dataDrawPanel=null;
    private CanvasPropertyPanel canvasPropertyPanel=null;
    
    public SettingPanelDialog() {
        super();
    }
    
    public SettingPanelDialog(JFrame owner,DataDrawPanel parent,String title,boolean modal){
        super(owner,title,modal);
        this.panel=(DataDrawPanel)parent;
        this.controller=panel.getController();
        this.timeLabelValue=controller.getTimeLabelValue();
        this.dataLabelValue=controller.getDataLabelValue();
        this.setLayout(new GridLayout(2, 1));
        dataDrawPanel=new DataPropertyPanel("Data Property");
        canvasPropertyPanel=new CanvasPropertyPanel("Canvas Property");
        this.add(dataDrawPanel);
        this.add(canvasPropertyPanel);
        //TODO Initial Position
        
        //TODO UI initial
        
        
        updateUI();
    }

    private void updateUI() {
        // TODO updateUI
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();

        this.pack();

    }
}

class DataPropertyPanel extends JPanel{
    public DataPropertyPanel() {}

    public DataPropertyPanel(String title) {
        this.setBorder(BorderFactory.createTitledBorder(title));
    }
}

class CanvasPropertyPanel extends JPanel{
    public CanvasPropertyPanel() {}

    public CanvasPropertyPanel(String title) {
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(1, 3));
        this.addPanel("LableName");
        this.addPanel("BaseColors");
        this.addPanel("Canvas");
        this.addPanel("Coordinate");
    }
}

