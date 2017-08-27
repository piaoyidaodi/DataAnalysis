package cc.comac.controller;

import javax.swing.JSplitPane;

import cc.comac.data.TargetDataPair;
import cc.comac.util.Functions;

public class DrawPanelController {
    
    private String targetLabelZipFilePath=null;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private int rangeXMin;
    private int rangeXMax;
    private double rangeYMin;
    private double rangeYMax;

    public DrawPanelController(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair) {
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.timeLabelValue=pair.getTimeLabelValue();
        this.dataLabelValue=pair.getDataLabelValue();
        this.rangeXMin=0;
        this.rangeXMax=timeLabelValue.length-1;
        // getMin and Max of the dataLabelValue
        this.rangeYMin=Functions.getDoubleArrayMin(dataLabelValue);
        this.rangeYMax=Functions.getDoubleArrayMax(dataLabelValue);
        
        
    }

    public void update(){
        
    }
    
    public String getTargetLabelZipFilePath() {
        return targetLabelZipFilePath;
    }

    public int getRangeXMin() {
        return rangeXMin;
    }

    public void setRangeXMin(int rangeXMin) {
        this.rangeXMin = rangeXMin;
    }

    public int getRangeXMax() {
        return rangeXMax;
    }

    public void setRangeXMax(int rangeXMax) {
        this.rangeXMax = rangeXMax;
    }

    public double getRangeYMin() {
        return rangeYMin;
    }

    public void setRangeYMin(double rangeYMin) {
        this.rangeYMin = rangeYMin;
    }

    public double getRangeYMax() {
        return rangeYMax;
    }

    public void setRangeYMax(double rangeYMax) {
        this.rangeYMax = rangeYMax;
    }

}
