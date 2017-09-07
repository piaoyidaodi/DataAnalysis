package cc.comac.ui.dialog;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import cc.comac.controller.DrawPanelController;
import cc.comac.ui.mainlayout.DataDrawPanel;

public class SettingPanelDialog extends JDialog {
    
    private DataDrawPanel panel;
    private DrawPanelController controller;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    
    public SettingPanelDialog() {
        super();
    }
    
    public SettingPanelDialog(JFrame owner,JComponent parent,String title,boolean modal){
        super(owner,title,modal);
        this.panel=(DataDrawPanel)parent;
        this.controller=panel.getController();
        this.timeLabelValue=controller.getTimeLabelValue();
        this.dataLabelValue=controller.getDataLabelValue();
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();
        updateUI();
    }

    private void updateUI() {
        // TODO updateUI
    }
}
