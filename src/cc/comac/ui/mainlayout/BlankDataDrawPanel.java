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
    private int canvasGeneralOffset=10;
    private int canvasSpecialOffset=35;
    
    private int xSpan=20;
    private int ySpan=10;

    private Color panelBGColor=null;
    private Color canvasBGColor=null;
    private Color coordinateColor=null;
    private Color scaleLineColor=null;
    
    public BlankDataDrawPanel(){
        super();
    }
    
    public BlankDataDrawPanel(DataSplitPane parent){
        this.rangeXMin=0;
        this.rangeXMax=1;
        this.rangeYMin=0;
        this.rangeYMax=1;
        // Light Gray with alpha 0.8
        this.panelBGColor=new Color(211,211,211,205);
        this.canvasBGColor=Color.white;
        this.coordinateColor=Color.darkGray;
        this.scaleLineColor=new Color(100,149,237,150);
        
        this.setBackground(panelBGColor);
        this.setMinimumSize(new Dimension(650, 200));
        this.setPreferredSize(new Dimension(PREFER_WIDTH, PREFER_HEIGHT));

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
        BasicStroke stroke=new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, miterLimit, dashPattern, dashPhase);
        g2.setStroke(stroke);
        g2.setColor(scaleLineColor);
        
        int xX=canvasPoints[0].x;
        for(int x=xSpan;xX+x<canvasPoints[2].x;x+=xSpan){
            Line2D line=new Line2D.Double(new Point(xX+x,canvasPoints[0].y), new Point(xX+x,canvasPoints[1].y));
            g2.draw(line);
        }
        int yY=canvasPoints[1].y;
        for(int y=ySpan;yY-y>canvasPoints[0].y;y+=ySpan){
            Line2D line=new Line2D.Double(new Point(canvasPoints[0].x,yY-y), new Point(canvasPoints[2].x,yY-y));
            g2.draw(line);
        }
    }

    protected void initCoordinate(Graphics2D g2) {
        int arrawOffset=3;
        g2.setColor(Color.gray);
        Line2D topCoordLine=new Line2D.Double(canvasPoints[0], canvasPoints[2]);
        Line2D rightCoordLine=new Line2D.Double(canvasPoints[2],canvasPoints[3]);
        g2.draw(topCoordLine);
        g2.draw(rightCoordLine);
        
        g2.setColor(coordinateColor);
        g2.setStroke(new BasicStroke(2.0F));
        Line2D leftCoordLine=new Line2D.Double(canvasPoints[0], canvasPoints[1]);
        Line2D BottomCoordLine=new Line2D.Double(canvasPoints[1],canvasPoints[3]);
        Line2D arrowTop=new Line2D.Double(canvasPoints[0], new Point(canvasPoints[0].x+arrawOffset, canvasPoints[0].y+arrawOffset));
        Line2D arrowRight=new Line2D.Double(canvasPoints[3], new Point(canvasPoints[3].x-arrawOffset, canvasPoints[3].y-arrawOffset));
        g2.draw(leftCoordLine);
        g2.draw(BottomCoordLine);
        g2.draw(arrowTop);
        g2.draw(arrowRight);
        
        int spotOffset=4;
        int xCoordWidth=canvasPoints[2].x-canvasPoints[0].x;
        int yCoordHeight=canvasPoints[1].y-canvasPoints[0].y;
        int xSpeSpan=xCoordWidth/(5*xSpan)*xSpan;
        int ySpeSpan=yCoordHeight/(5*ySpan)*ySpan;
        for(int i=0;i<=xCoordWidth;i+=xSpeSpan){
            Line2D spot=new Line2D.Double(new Point(canvasPoints[0].x+i, canvasPoints[1].y), new Point(canvasPoints[0].x+i, canvasPoints[1].y-spotOffset));
            g2.draw(spot);
        }
        for(int i=canvasPoints[1].y;i>=0;i-=ySpeSpan){
            Line2D spot=new Line2D.Double(new Point(canvasPoints[0].x, i), new Point(canvasPoints[0].x+spotOffset, i));
            g2.draw(spot);
        }
    }

    protected void initCanvas(Graphics2D g2) {
        Rectangle2D rectangle2d=new Rectangle2D.Double();
        rectangle2d.setFrameFromDiagonal(canvasPoints[0], canvasPoints[3]);
        g2.setColor(canvasBGColor);
        g2.fill(rectangle2d);
    }

    private void initPanelPoints() {
        for (int i=0;i<4;i++) {
            panelPoints[i]=this.getLocation();
            canvasPoints[i]=this.getLocation();
        }

        panelLTX=this.getX();
        panelLTY=this.getY();
        panelWidth=this.getWidth();
        panelHeiht=this.getHeight();

        panelPoints[0]=this.getLocation();
        panelPoints[1].setLocation(panelLTX, panelLTY+panelHeiht);
        panelPoints[2].setLocation(panelLTX+panelWidth, panelLTY);
        panelPoints[3].setLocation(panelLTX+panelWidth, panelLTY+panelHeiht);
        
        canvasPoints[0].setLocation(panelLTX+canvasSpecialOffset, panelLTY+canvasGeneralOffset);
        canvasPoints[1].setLocation(panelLTX+canvasSpecialOffset, panelLTY+panelHeiht-canvasSpecialOffset);
        canvasPoints[2].setLocation(panelLTX+panelWidth-canvasGeneralOffset, panelLTY+canvasGeneralOffset);
        canvasPoints[3].setLocation(panelLTX+panelWidth-canvasGeneralOffset, panelLTY+panelHeiht-canvasSpecialOffset);
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
