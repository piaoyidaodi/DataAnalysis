package cc.comac.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.JSplitPane;

import cc.comac.data.TargetDataPair;

public class DrawPanelController {
    protected static int xSpanNum=8;
    protected static int ySpanNum=5;
    
    private String targetLabelZipFilePath=null;
    private String targetLabelZipFileName=null;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;

    private int timeNum;
    private double dataMin;
    private double dataMax;
    
    private String[] xLabels=new String[xSpanNum+1];
    private Double[] yLabels=new Double[ySpanNum+1];
    // Blank Base Panel Setting
    private Color panelBGColor=null;
    private Color canvasBGColor=null;
    private Color coordinateColor=null;
    private Color scaleLineColor=null;
    
    private int canvasGeneralOffset;
    private int canvasSpecialXOffset;
    private int canvasSpecialYOffset;
    
    
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private Color labelFontColor;
    
    private Color lineColor;
    private float lineWidth;

    private String labelNameFontName;
    private int labelNameFontStyle;
    private int labelNameFontSize;
    private Color labelNameFontColor;
    
    public DrawPanelController(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair) {
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.targetLabelZipFileName=targetLabelZipFilePath.substring(targetLabelZipFilePath.lastIndexOf(File.separator)+1,targetLabelZipFilePath.lastIndexOf("."));
        this.timeLabelValue=pair.getTimeLabelValue();
        this.dataLabelValue=pair.getDataLabelValue();
        //TODO Reset para
        this.timeIndexMin=0;
        this.timeIndexMax=timeLabelValue.length-1;
        
        this.panelBGColor=new Color(211,211,211,205);
        this.canvasBGColor=Color.white;
        this.coordinateColor=Color.darkGray;
        this.scaleLineColor=new Color(100,149,237,150);
        
        this.canvasGeneralOffset=10;
        this.canvasSpecialXOffset=35;
        this.canvasSpecialYOffset=50;
        
        this.setFontName("SansSerif");
        this.setFontStyle(Font.BOLD);
        this.setFontSize(12);
        this.setLabelFontColor(Color.black);
        
        this.setLineColor(Color.red);
        this.setLineWidth(1.0F);
        
        this.setLabelNameFontName("SansSerif");
        this.setLabelNameFontStyle(Font.BOLD);
        this.setLabelNameFontSize(12);
        this.setLabelNameFontColor(Color.red);
        
        update();
    }
    
    public void update(){
        this.setTimeNum(timeIndexMax-timeIndexMin+1);
        analysisDataRange();
        updateXYLabel();
    }
    
    private void analysisDataRange(){
        double min=dataLabelValue[timeIndexMin];
        double max=dataLabelValue[timeIndexMin];
        for(int i=timeIndexMin;i<=timeIndexMax;i++){
            if (dataLabelValue[i]<min) {
                min=dataLabelValue[i];
            }else if (dataLabelValue[i]>=max) {
                max=dataLabelValue[i];
            }
        }
        this.dataMin=min;
        this.dataMax=max;
//        System.out.println("timeIndexMin:"+timeIndexMin);
//        System.out.println("timeIndexMax:"+timeIndexMax);
//        System.out.println("dataMin:"+dataMin);
//        System.out.println("dataMax:"+dataMax);
//        System.out.println("--------------");
    }
    
    private void updateXYLabel(){
        int xSpan=(timeIndexMax-timeIndexMin)/xSpanNum;
        double ySpan=(dataMax-dataMin)/ySpanNum;
        int xStep=xSpan;
        double yStep=ySpan;
        
        xLabels[0]=timeLabelValue[timeIndexMin];
        xLabels[xSpanNum]=timeLabelValue[timeIndexMax];

        for(int i=1;i<xSpanNum;i++){
            xLabels[i]=timeLabelValue[timeIndexMin+xStep];
            xStep+=xSpan;
        }
        
        if (dataMin==dataMax) {
            yLabels[0]=dataMin;
            yLabels[ySpanNum]=dataMax+1;
        }else {
            yLabels[0]=dataMin;
            yLabels[ySpanNum]=dataMax;
            for(int i=1;i<ySpanNum;i++){
                yLabels[i]=dataMin+yStep;
                yStep+=ySpan;
            }
        }
    }
    
    public int getTimeIndexMin() {
        return timeIndexMin;
    }

    public void setTimeIndexMin(int timeIndexMin) {
        this.timeIndexMin = timeIndexMin;
    }

    public int getTimeIndexMax() {
        return timeIndexMax;
    }

    public void setTimeIndexMax(int timeIndexMax) {
        this.timeIndexMax = timeIndexMax;
    }

    public double getDataMin() {
        return dataMin;
    }

    public double getDataMax() {
        return dataMax;
    }

    public String getTargetLabelZipFilePath() {
        return targetLabelZipFilePath;
    }
    
    public String getTargetLabelZipFileName() {
        return targetLabelZipFileName;
    }
    
    public String[] getTimeLabelValue() {
        return timeLabelValue;
    }

    public Double[] getDataLabelValue() {
        return dataLabelValue;
    }

    public String[] getxLabels() {
        return xLabels;
    }

    public Double[] getyLabels() {
        return yLabels;
    }

    public int getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getPanelBGColor() {
        return panelBGColor;
    }

    public void setPanelBGColor(Color panelBGColor) {
        this.panelBGColor = panelBGColor;
    }

    public Color getCanvasBGColor() {
        return canvasBGColor;
    }

    public void setCanvasBGColor(Color canvasBGColor) {
        this.canvasBGColor = canvasBGColor;
    }

    public Color getCoordinateColor() {
        return coordinateColor;
    }

    public void setCoordinateColor(Color coordinateColor) {
        this.coordinateColor = coordinateColor;
    }

    public Color getScaleLineColor() {
        return scaleLineColor;
    }

    public void setScaleLineColor(Color scaleLineColor) {
        this.scaleLineColor = scaleLineColor;
    }

    public int getCanvasGeneralOffset() {
        return canvasGeneralOffset;
    }

    public void setCanvasGeneralOffset(int canvasGeneralOffset) {
        this.canvasGeneralOffset = canvasGeneralOffset;
    }

    public int getCanvasSpecialXOffset() {
        return canvasSpecialXOffset;
    }

    public void setCanvasSpecialXOffset(int canvasSpecialXOffset) {
        this.canvasSpecialXOffset = canvasSpecialXOffset;
    }

    public int getCanvasSpecialYOffset() {
        return canvasSpecialYOffset;
    }

    public void setCanvasSpecialYOffset(int canvasSpecialYOffset) {
        this.canvasSpecialYOffset = canvasSpecialYOffset;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public Color getLabelFontColor() {
        return labelFontColor;
    }

    public void setLabelFontColor(Color labelFontColor) {
        this.labelFontColor = labelFontColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLabelNameFontName() {
        return labelNameFontName;
    }

    public void setLabelNameFontName(String labelNameFont) {
        this.labelNameFontName = labelNameFont;
    }

    public int getLabelNameFontStyle() {
        return labelNameFontStyle;
    }

    public void setLabelNameFontStyle(int labelNameFontStyle) {
        this.labelNameFontStyle = labelNameFontStyle;
    }

    public int getLabelNameFontSize() {
        return labelNameFontSize;
    }

    public void setLabelNameFontSize(int labelNameFontSize) {
        this.labelNameFontSize = labelNameFontSize;
    }

    public Color getLabelNameFontColor() {
        return labelNameFontColor;
    }

    public void setLabelNameFontColor(Color labelNameFontColor) {
        this.labelNameFontColor = labelNameFontColor;
    }

}
