package cc.comac.ui.mainlayout;

import cc.comac.controller.DrawPanelController;

public class DataDrawPanel extends BlankDataDrawPanel {
    
    private String targetLabelZipFilePath;
    private DrawPanelController controller;
    
    public DataDrawPanel() {}

    public DataDrawPanel(DataSplitPane parent,String targetLabelZipFilePath, DrawPanelController controller) {
        super(parent);
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.controller=controller;
        this.init();
    }

    private void init() {
        // Initial the DrawPanel
        System.out.println("Draw"+targetLabelZipFilePath);
        
    }
}
