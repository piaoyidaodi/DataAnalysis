package cc.comac.ui.mainlayout;

import javax.swing.JPanel;

import cc.comac.controller.DrawPanelController;

public class DataDrawPanel extends JPanel {
    
    private String targetLabelZipFilePath;
    private DrawPanelController controller;
    
    public DataDrawPanel() {}

    public DataDrawPanel(String targetLabelZipFilePath, DrawPanelController controller) {
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.controller=controller;
        this.init();
    }

    private void init() {
        // Initial the DrawPanel
        System.out.println("Draw"+targetLabelZipFilePath);
        
    }
}
