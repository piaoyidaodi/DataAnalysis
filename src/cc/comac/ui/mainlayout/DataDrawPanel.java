package cc.comac.ui.mainlayout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import cc.comac.controller.DrawPanelController;
import cc.comac.ui.dialog.SettingPanelDialog;
import cc.comac.ui.popupmenu.CenterPanePopupMenu;
import cc.comac.util.Context;

public class DataDrawPanel extends BlankDataDrawPanel {
    
    private DrawPanelController controller;
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    private double dataMin;
    private double dataMax;
    private String targetLabelZipFileName;
        
    private String[] xLabels=new String[xSpanNum+1];
    private Double[] yLabels=new Double[ySpanNum+1];
    
    private Font labelFont;
    private Color labelFontColor;
    private int fontSize;

    private Color lineColor;
    private float lineWidth;
    
    private Font labelNameFont;
    private Color labelNameFontColor;

    private SettingPanelDialog dialog;
    
    public DataDrawPanel() {}

    public DataDrawPanel(DataSplitPane parent,String targetLabelZipFilePath, DrawPanelController controller) {
        super(parent);
        this.dataLabelValue=controller.getDataLabelValue();
        this.controller=controller;
        dialog=new SettingPanelDialog(Context.getInstance().getMainFrame(), this, "Setting Panel Property", true);
        init();
        this.addListener();        
    }
    
    private void init(){
        this.targetLabelZipFileName=controller.getTargetLabelZipFileName();
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();
        this.dataMin=controller.getDataMin();
        this.dataMax=controller.getDataMax();
        this.xLabels=controller.getxLabels();
        this.yLabels=controller.getyLabels();
        this.fontSize=controller.getFontSize();
        // BlankDataDraw Colors
        this.panelBGColor=controller.getPanelBGColor();
        this.canvasBGColor=controller.getCanvasBGColor();
        this.coordinateColor=controller.getCoordinateColor();
        this.scaleLineColor=controller.getScaleLineColor();
        this.setBackground(panelBGColor);
        // Data Properties
        this.labelFont=new Font(controller.getFontName(), controller.getFontStyle(), controller.getFontSize());
        this.labelFontColor=controller.getLabelFontColor();
        
        this.canvasGeneralOffset=controller.getCanvasGeneralOffset();
        this.canvasSpecialXOffset=controller.getCanvasSpecialXOffset();
        this.canvasSpecialYOffset=controller.getCanvasSpecialYOffset();
        
        this.lineColor=controller.getLineColor();
        this.lineWidth=controller.getLineWidth();
        // LabelName
        this.labelNameFont=new Font(controller.getLabelNameFontName(), controller.getLabelNameFontStyle(), controller.getLabelNameFontSize());
        this.labelNameFontColor=controller.getLabelNameFontColor();
    }
    
    public void update(){
        controller.update();
        init();
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2=(Graphics2D) g;
        drawNameLabel(g2);
        drawLines(g2);
        drawTimeLabels(g2);
        drawDataLabels(g2);
    }
    
    protected void drawNameLabel(Graphics2D g2) {
        targetLabelZipFileName=controller.getLabelName();
        g2.setFont(labelNameFont);
        g2.setColor(labelNameFontColor);
//        canvasPoints[2].x-nameXOffset*targetLabelZipFileName.length(), canvasPoints[2].y+nameYOffset
        g2.drawString(targetLabelZipFileName, panelWidth*2/5,panelHeight-5);
    }

    protected void drawLines(Graphics2D g2) {
        int timeIndexNumber=timeIndexMax-timeIndexMin+1;
        System.out.println("time Index Number:"+timeIndexNumber);
        g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(lineWidth));

        if (dataMin==dataMax) {
            g2.drawLine(canvasPoints[1].x, canvasPoints[1].y-1, canvasPoints[3].x, canvasPoints[3].y-1);
        }else{
            if (timeIndexNumber>=canvasWidth) {
                int xPervious=canvasPoints[1].x;
                int yPervious=canvasPoints[1].y-(int)((dataLabelValue[timeIndexMin]-dataMin)/(dataMax-dataMin)*canvasHeight);
                for(int i=timeIndexMin+1;i<=timeIndexMax;i++){
                    int xRear=canvasPoints[1].x+(int)(canvasWidth*1.0/timeIndexNumber*(i-timeIndexMin));
                    int yRear=canvasPoints[1].y-(int)((dataLabelValue[i]-dataMin)/(dataMax-dataMin)*canvasHeight);
                    g2.drawLine(xPervious, yPervious, xRear, yRear);
                    xPervious=xRear;
                    yPervious=yRear;
                }
            }else {
                int xPervious=canvasPoints[1].x;
                int yPervious=canvasPoints[1].y-(int)((dataLabelValue[timeIndexMin]-dataMin)/(dataMax-dataMin)*canvasHeight);
                for(int i=timeIndexMin+1;i<=timeIndexMax;i++){
                  int xRear=canvasPoints[1].x+(int)(canvasWidth*1.0/timeIndexNumber*(i-timeIndexMin));
                  int yRear=canvasPoints[1].y-(int)((dataLabelValue[i]-dataMin)/(dataMax-dataMin)*canvasHeight);
                  g2.drawLine(xPervious, yPervious, xRear, yRear);
                  xPervious=xRear;
                  yPervious=yRear;
              }
            }
        }
    }

    protected void drawTimeLabels(Graphics2D g2){
        int xXOffset=fontSize/2*4;
        int xYOffset=fontSize+1;
        g2.setFont(labelFont);
        g2.setColor(labelFontColor);
        for (int i=0;i<=xSpanNum;i++) {
            String labelName=xLabels[i].substring(0,8);
            if (i==0) {
                Point labelPosition=coordXPoints[0];
                g2.drawString(labelName, labelPosition.x-10, labelPosition.y+xYOffset);
            }else if(i==xSpanNum){
                Point labelPosition=coordXPoints[xSpanNum];
                g2.drawString(labelName, labelPosition.x-40, labelPosition.y+xYOffset);
            }else{
                Point labelPosition=coordXPoints[i];
                g2.drawString(labelName, labelPosition.x-xXOffset, labelPosition.y+xYOffset);
            }
        }
    }
    
    protected void drawDataLabels(Graphics2D g2){
        int yXOffset=fontSize/2+1;
        int yYOffset=fontSize/2;
        g2.setFont(labelFont);
        g2.setColor(labelFontColor);
        
        if (dataMin==dataMax) {
            String labelName=yLabels[0].toString();
            int labelNameLength=labelName.toString().length();
            if (labelNameLength>6) {
                labelNameLength=6;
            }
            g2.drawString(yLabels[0].toString().substring(0,labelNameLength), coordYPoints[0].x-yLabels[0].toString().length()*yYOffset, coordYPoints[0].y);
            g2.drawString(yLabels[ySpanNum].toString(), coordYPoints[ySpanNum].x-yLabels[ySpanNum].toString().length()*yYOffset, coordYPoints[ySpanNum].y+yYOffset);
        }else{
            for (int i=0;i<=ySpanNum;i++) {
                String labelName=yLabels[i].toString();
                int labelNameLength=labelName.toString().length();
                if (labelNameLength>6) {
                    labelNameLength=6;
                }
                if (i==0) {
                    Point labelPosition=coordYPoints[0];
                    g2.drawString(labelName.substring(0,labelNameLength), labelPosition.x-labelNameLength*yXOffset, labelPosition.y);
                }else{
                    Point labelPosition=coordYPoints[i];
                    g2.drawString(labelName.substring(0,labelNameLength), labelPosition.x-labelNameLength*yXOffset, labelPosition.y+yYOffset);
                }
            }
        }
    }
    
    private void addListener(){
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    pPre=e.getPoint();
                    if (Context.getInstance().isSelectCursor()) {
                        if(pPre.x<canvasPoints[0].x||pPre.x>canvasPoints[3].x){
                            pPre=canvasPoints[0];
                        }
                    }
                }else if ((e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0) {
                    CenterPanePopupMenu menu=new CenterPanePopupMenu(DataDrawPanel.this);
                    menu.show(DataDrawPanel.this, e.getX(), e.getY());
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    pBhd=e.getPoint();
                    int tmpTimeIndexMin=controller.getTimeIndexMin();
                    int tmpTimeIndexMax=controller.getTimeIndexMax();

                    if (Context.getInstance().isSelectCursor()) {
                        if(pBhd.x<canvasPoints[0].x||pPre.x>canvasPoints[3].x){
                            pPre=canvasPoints[3];
                        }
                        
                        if (pPre.x>=pBhd.x) {
                            tmpTimeIndexMin=0;
                            tmpTimeIndexMax=controller.getTimeLabelValue().length-1;
                        }else {
                            if (controller.getTimeNum()>=DataDrawPanel.this.canvasWidth){
                                int tmpM1=controller.getTimeNum()/DataDrawPanel.this.canvasWidth*(pPre.x-canvasPoints[0].x)+tmpTimeIndexMin;
                                int tmpM2=controller.getTimeNum()/DataDrawPanel.this.canvasWidth*(pBhd.x-canvasPoints[0].x)+tmpTimeIndexMin;
                                tmpTimeIndexMin=tmpM1;
                                tmpTimeIndexMax=tmpM2;
                            }
                        }
                    }else if(!Context.getInstance().isSelectCursor()) {
                        int xRange=pPre.x-pBhd.x;
                        int maxRange=controller.getTimeLabelValue().length-1;
                        if (xRange<=0) {
                            xRange=-xRange;
                            if (tmpTimeIndexMin<=xRange) {
                                tmpTimeIndexMin=0;
                            }else {
                                tmpTimeIndexMin-=xRange;
                                tmpTimeIndexMax-=xRange;
                            }
                        }else {
                            if (tmpTimeIndexMax+xRange>=maxRange) {
                                tmpTimeIndexMax=maxRange;
                            }else{
                                tmpTimeIndexMax+=xRange;
                                tmpTimeIndexMin+=xRange;
                            }
                        }
                    }
                    controller.setTimeIndexMin(tmpTimeIndexMin);
                    controller.setTimeIndexMax(tmpTimeIndexMax);
                    System.out.println("press "+pPre.toString());
                    System.out.println("Release "+pBhd.toString());
                    EventQueue.invokeLater(new Runnable() {
                        
                        @Override
                        public void run() {
                            DataDrawPanel.this.update();
                        }
                    });
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0) {
                    CenterPanePopupMenu menu=new CenterPanePopupMenu(DataDrawPanel.this);
                    menu.show(DataDrawPanel.this, e.getX(), e.getY());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (Context.getInstance().isSelectCursor()) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
            
        });
        
        this.addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics2D g2=(Graphics2D)DataDrawPanel.this.getGraphics();
                System.out.println("Mouse Drag "+e.getX());
                if (Context.getInstance().isSelectCursor()) {
                    pBhd=e.getPoint();
                    float[] dashPattern={1.0F,1.0F,1.0F,1.0F};
                    float miterLimit=10.0F;
                    float dashPhase=0;
                    BasicStroke stroke=new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, miterLimit, dashPattern, dashPhase);
                    g2.setStroke(stroke);
                    g2.setColor(new Color(0,128,0, 150));
                    g2.drawRect(pPre.x, pPre.y, pBhd.x-pPre.x, pBhd.y-pPre.y);
                    EventQueue.invokeLater(new Runnable() {
                        
                        @Override
                        public void run() {
                            DataDrawPanel.this.update();
                        }
                    });
                }
            }
        });
        
        this.addMouseWheelListener(new MouseWheelListener() {
            
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // TODO ADD Wheel Move Actions
//                Graphics2D g2=(Graphics2D)DataDrawPanel.this.getGraphics();
                if (!Context.getInstance().isSelectCursor()) {
                    
                }
            }
        });
        
    }

    public DrawPanelController getController() {
        return controller;
    }

    public SettingPanelDialog getDialog() {
        return dialog;
    }

}
