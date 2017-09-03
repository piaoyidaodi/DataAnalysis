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
    protected static int xSpanNum=8;
    protected static int ySpanNum=5;
    
    protected Point[] panelPoints=new Point[4];
    protected int panelLTX;
    protected int panelLTY;
    protected int panelWidth;
    protected int panelHeight;
    
    protected Point[] canvasPoints=new Point[4];
    protected int canvasGeneralOffset=10;
    protected int canvasSpecialXOffset=35;
    protected int canvasSpecialYOffset=50;
    protected int canvasWidth;
    protected int canvasHeight;
    
    protected Point[] coordXPoints=new Point[xSpanNum+1];
    protected Point[] coordYPoints=new Point[ySpanNum+1];
    protected int xSpeSpan;
    protected int ySpeSpan;
    protected int xSpan;
    protected int ySpan;

    protected Color panelBGColor=null;
    protected Color canvasBGColor=null;
    protected Color coordinateColor=null;
    protected Color scaleLineColor=null;
    
    public BlankDataDrawPanel(){
        super();
    }
    
    public BlankDataDrawPanel(DataSplitPane parent){
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
        initPoints();
        initCanvas(g2);
        initCoordinate(g2);
        initScale(g2);

    }
    
    private void initScale(Graphics2D g2) {
        float[] dashPattern={2.0F,2.0F,2.0F,2.0F};
        float miterLimit=10.0F;
        float dashPhase=0;
        BasicStroke stroke=new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, miterLimit, dashPattern, dashPhase);
        g2.setStroke(stroke);
        g2.setColor(scaleLineColor);
        
        this.xSpan=xSpeSpan/10;
        this.ySpan=ySpeSpan/5;

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

    private void initCoordinate(Graphics2D g2) {
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
        int loopindex=0;
        int xCoordWidth=canvasPoints[2].x-canvasPoints[0].x;
        int yCoordHeight=canvasPoints[1].y-canvasPoints[0].y;
        this.xSpeSpan=xCoordWidth/xSpanNum+1;
        this.ySpeSpan=yCoordHeight/ySpanNum+1;
        
        for(int i=0;i<=xCoordWidth;i+=xSpeSpan){
            Line2D spot=new Line2D.Double(new Point(canvasPoints[0].x+i, canvasPoints[1].y), new Point(canvasPoints[0].x+i, canvasPoints[1].y-spotOffset));
            coordXPoints[loopindex++]=new Point(canvasPoints[0].x+i, canvasPoints[1].y);
            g2.draw(spot);
        }
        coordXPoints[xSpanNum]=canvasPoints[3];
        loopindex=0;
        for(int i=0;i<=yCoordHeight;i+=ySpeSpan){
            Line2D spot=new Line2D.Double(new Point(canvasPoints[1].x, canvasPoints[1].y-i), new Point(canvasPoints[1].x+spotOffset, canvasPoints[1].y-i));
            coordYPoints[loopindex++]=new Point(canvasPoints[1].x, canvasPoints[1].y-i);
            g2.draw(spot);
        }
        coordYPoints[ySpanNum]=canvasPoints[0];
    }

    private void initCanvas(Graphics2D g2) {
        Rectangle2D rectangle2d=new Rectangle2D.Double();
        rectangle2d.setFrameFromDiagonal(canvasPoints[0], canvasPoints[3]);
        this.canvasWidth=canvasPoints[2].x-canvasPoints[0].x;
        this.canvasHeight=canvasPoints[1].y-canvasPoints[0].y;
        g2.setColor(canvasBGColor);
        g2.fill(rectangle2d);
    }

    private void initPoints() {
        for (int i=0;i<4;i++) {
            panelPoints[i]=this.getLocation();
            canvasPoints[i]=this.getLocation();
        }

        panelLTX=this.getX();
        panelLTY=this.getY();
        panelWidth=this.getWidth();
        panelHeight=this.getHeight();

        panelPoints[0]=this.getLocation();
        panelPoints[1].setLocation(panelLTX, panelLTY+panelHeight);
        panelPoints[2].setLocation(panelLTX+panelWidth, panelLTY);
        panelPoints[3].setLocation(panelLTX+panelWidth, panelLTY+panelHeight);
        
        canvasPoints[0].setLocation(panelLTX+canvasSpecialYOffset, panelLTY+canvasGeneralOffset);
        canvasPoints[1].setLocation(panelLTX+canvasSpecialYOffset, panelLTY+panelHeight-canvasSpecialXOffset);
        canvasPoints[2].setLocation(panelLTX+panelWidth-canvasGeneralOffset, panelLTY+canvasGeneralOffset);
        canvasPoints[3].setLocation(panelLTX+panelWidth-canvasGeneralOffset, panelLTY+panelHeight-canvasSpecialXOffset);
    }

}
