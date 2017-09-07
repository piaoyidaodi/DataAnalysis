package cc.comac.controller;

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

    public DrawPanelController(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair) {
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.targetLabelZipFileName=targetLabelZipFilePath.substring(targetLabelZipFilePath.lastIndexOf(File.separator)+1,targetLabelZipFilePath.lastIndexOf("."));
        this.timeLabelValue=pair.getTimeLabelValue();
        this.dataLabelValue=pair.getDataLabelValue();
        this.timeIndexMin=0;
        this.timeIndexMax=timeLabelValue.length-1;
        update();
    }
    
    public void update(){
//        this.baseTimeMin=timeIndexMin;
//        this.baseTimeMax=timeIndexMax;
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
        System.out.println("timeIndexMin:"+timeIndexMin);
        System.out.println("timeIndexMax:"+timeIndexMax);
        System.out.println("dataMin:"+dataMin);
        System.out.println("dataMax:"+dataMax);
        System.out.println("--------------");
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

}
