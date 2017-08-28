package cc.comac.ui.mainlayout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import cc.comac.util.Context;

public class BlankDataDrawPanel extends JPanel{
    private MainFrameCenterPane parent=Context.getInstance().getMainFrameCenterPane();
    private final int PREFER_WIDTH=800;
    private final int PREFER_HEIGHT=250;
    private int rangeXMin;
    private int rangeXMax;
    private double rangeYMin;
    private double rangeYMax;
    
    public BlankDataDrawPanel(){
        super();
    }
    
    public BlankDataDrawPanel(DataSplitPane parent){
        this.rangeXMin=0;
        this.rangeXMax=1;
        this.rangeYMin=0;
        this.rangeYMax=1;
        this.init();
        this.setPreferredSize(new Dimension(PREFER_WIDTH, PREFER_HEIGHT));
        this.setMinimumSize(new Dimension(PREFER_WIDTH, PREFER_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
    }
    private void init(){
        System.out.println(parent.getX());
        System.out.println(parent.getY());
        System.out.println(parent.getWidth());
        System.out.println(parent.getHeight());
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
