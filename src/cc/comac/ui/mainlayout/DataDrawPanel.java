package cc.comac.ui.mainlayout;

import java.awt.Graphics;

import cc.comac.controller.DrawPanelController;

public class DataDrawPanel extends BlankDataDrawPanel {
    
    private String targetLabelZipFilePath;
    private DrawPanelController controller;
    
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    private int dataIndexMin;
    private int dataIndexMax;
    
    private String[] timeLabel=new String[9];
    private Double[] dataLabel=new Double[7];
    
    public DataDrawPanel() {}

    public DataDrawPanel(DataSplitPane parent,String targetLabelZipFilePath, DrawPanelController controller) {
        super(parent);
        
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.controller=controller;
        timeLabelValue=controller.getTimeLabelValue();
        dataLabelValue=controller.getDataLabelValue();
        this.timeIndexMin=0;
        this.timeIndexMax=timeLabelValue.length;
        this.dataIndexMin=0;
        this.dataIndexMax=dataLabelValue.length;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTimeLabels();
    }
    
    protected void drawTimeLabels(){
        int xCoordWidth=this.canvasWidth;
        int xDrawSpan=xCoordWidth/xSpanNum;
    }
    
    protected void drawDataLabels(){
        int yCoordWidth=this.canvasHeight;
        int yDrawSpan=yCoordWidth/ySpanNum;
    }
    
}
