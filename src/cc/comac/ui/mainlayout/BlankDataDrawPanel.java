package cc.comac.ui.mainlayout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class BlankDataDrawPanel extends JPanel{
    private final int PREFER_WIDTH=650;
    private final int PREFER_HEIGHT=400;
    private int rangeXMin;
    private int rangeXMax;
    private double rangeYMin;
    private double rangeYMax;
    
    private Point[] panelPoints=new Point[4];
    private int panelLTX;
    private int panelLTY;
    private int panelWidth;
    private int panelHeiht;
    
    private Point[] canvasPoints=new Point[4];
    private int canvasOffset=30;
    
    private Point[] coordinatePoints=new Point[4];
    private int coordinateOffset=30;
    
    private int xSpan=20;
    private int ySpan=10;

    
    public BlankDataDrawPanel(){
        super();
    }
    
    public BlankDataDrawPanel(DataSplitPane parent){
        this.rangeXMin=0;
        this.rangeXMax=1;
        this.rangeYMin=0;
        this.rangeYMax=1;
        this.setMinimumSize(new Dimension(650, 200));
        this.setPreferredSize(new Dimension(PREFER_WIDTH, PREFER_HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.init();
    }
    private void init(){
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        initPanelPoints();
        initCanvas(g2);
        initCoordinate(g2);
        initScale(g2);

    }
    
    protected void initScale(Graphics2D g2) {
        float[] dashPattern={2.0F,2.0F,2.0F,2.0F};
        float miterLimit=10.0F;
        float dashPhase=0;
        BasicStroke stroke=new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, miterLimit, dashPattern, dashPhase);
        g2.setStroke(stroke);
        g2.setColor(Color.blue);
        
        int xX=coordinatePoints[0].x;
        for(int x=xSpan;xX+x<coordinatePoints[2].x;x+=xSpan){
            Line2D line=new Line2D.Double(new Point(xX+x,coordinatePoints[0].y), new Point(xX+x,coordinatePoints[1].y));
            g2.draw(line);
        }
        int yY=coordinatePoints[0].y;
        for(int y=ySpan;yY+y<coordinatePoints[1].y;y+=ySpan){
            Line2D line=new Line2D.Double(new Point(coordinatePoints[0].x,yY+y), new Point(coordinatePoints[2].x,yY+y));
            g2.draw(line);
        }
    }

    protected void initCoordinate(Graphics2D g2) {
        int offset=3;
        g2.setColor(Color.gray);
        Line2D topCoordLine=new Line2D.Double(coordinatePoints[0], coordinatePoints[2]);
        Line2D rightCoordLine=new Line2D.Double(coordinatePoints[2],coordinatePoints[3]);
        g2.draw(topCoordLine);
        g2.draw(rightCoordLine);
        
        g2.setColor(Color.darkGray);
        g2.setStroke(new BasicStroke(2.0F));
        Line2D leftCoordLine=new Line2D.Double(coordinatePoints[0], coordinatePoints[1]);
        Line2D BottomCoordLine=new Line2D.Double(coordinatePoints[1],coordinatePoints[3]);
        Line2D arrowTop=new Line2D.Double(coordinatePoints[0], new Point(coordinatePoints[0].x+offset, coordinatePoints[0].y+offset));
        Line2D arrowRight=new Line2D.Double(coordinatePoints[3], new Point(coordinatePoints[3].x-offset, coordinatePoints[3].y-offset));
        g2.draw(leftCoordLine);
        g2.draw(BottomCoordLine);
        g2.draw(arrowTop);
        g2.draw(arrowRight);
        
        //TODO Xcoordinate node every 3*xSpan pixels one;Ycoordinate node every 3*ySpan
    }

    protected void initCanvas(Graphics2D g2) {
        Rectangle2D rectangle2d=new Rectangle2D.Double();
        rectangle2d.setFrameFromDiagonal(canvasPoints[0], canvasPoints[3]);
        g2.setColor(Color.white);
        g2.fill(rectangle2d);
    }

    private void initPanelPoints() {
        for (int i=0;i<4;i++) {
            panelPoints[i]=this.getLocation();
            canvasPoints[i]=this.getLocation();
            coordinatePoints[i]=this.getLocation();
        }

        panelLTX=this.getX();
        panelLTY=this.getY();
        panelWidth=this.getWidth();
        panelHeiht=this.getHeight();

        panelPoints[0]=this.getLocation();
        panelPoints[1].setLocation(panelLTX, panelLTY+panelHeiht);
        panelPoints[2].setLocation(panelLTX+panelWidth, panelLTY);
        panelPoints[3].setLocation(panelLTX+panelWidth, panelLTY+panelHeiht);
        
        canvasPoints[0].setLocation(panelLTX+canvasOffset, panelLTY+canvasOffset);
        canvasPoints[1].setLocation(panelLTX+canvasOffset, panelLTY+panelHeiht-canvasOffset);
        canvasPoints[2].setLocation(panelLTX+panelWidth-canvasOffset, panelLTY+canvasOffset);
        canvasPoints[3].setLocation(panelLTX+panelWidth-canvasOffset, panelLTY+panelHeiht-canvasOffset);
        
        coordinatePoints[0].setLocation(panelLTX+coordinateOffset, panelLTY+coordinateOffset);
        coordinatePoints[1].setLocation(panelLTX+coordinateOffset, panelLTY+panelHeiht-coordinateOffset);
        coordinatePoints[2].setLocation(panelLTX+panelWidth-coordinateOffset, panelLTY+coordinateOffset);
        coordinatePoints[3].setLocation(panelLTX+panelWidth-coordinateOffset, panelLTY+panelHeiht-coordinateOffset);
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
