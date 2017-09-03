package cc.comac.ui.mainlayout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import cc.comac.controller.DrawPanelController;
import cc.comac.ui.popupmenu.CenterPanePopupMenu;

public class DataDrawPanel extends BlankDataDrawPanel {
    
    private DrawPanelController controller;
    
    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    private double dataMin;
    private double dataMax;
        
    private String[] xLabels=new String[xSpanNum+1];
    private Double[] yLabels=new Double[ySpanNum+1];
    
    private int fontSize;
    
    public DataDrawPanel() {}

    public DataDrawPanel(DataSplitPane parent,String targetLabelZipFilePath, DrawPanelController controller) {
        super(parent);
        
        this.controller=controller;
        dataLabelValue=controller.getDataLabelValue();
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();
        this.dataMin=controller.getDataMin();
        this.dataMax=controller.getDataMax();
        this.xLabels=controller.getxLabels();
        this.yLabels=controller.getyLabels();
        this.fontSize=12;
        this.addListener();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        drawTimeLabels(g2);
        drawDataLabels(g2);
        drawLines(g2);
    }
    
    protected void drawLines(Graphics2D g2) {
        int timeIndexNumber=timeIndexMax-timeIndexMin+1;
        
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1.0F));

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
        g2.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        g2.setColor(Color.black);
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
        g2.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        g2.setColor(Color.black);
        
        if (dataMin==dataMax) {
            g2.drawString(yLabels[0].toString(), coordYPoints[0].x-yLabels[0].toString().length()*yYOffset, coordYPoints[0].y);
            g2.drawString(yLabels[ySpanNum].toString(), coordYPoints[ySpanNum].x-yLabels[ySpanNum].toString().length()*yYOffset, coordYPoints[ySpanNum].y+yYOffset);
        }else{
            for (int i=0;i<=ySpanNum;i++) {
                String labelName=yLabels[i].toString();
                int labelNameLength=labelName.toString().length();
                if (i==0) {
                    Point labelPosition=coordYPoints[0];
                    g2.drawString(labelName, labelPosition.x-labelNameLength*yXOffset, labelPosition.y);
                }else{
                    Point labelPosition=coordYPoints[i];
                    g2.drawString(labelName, labelPosition.x-labelNameLength*yXOffset, labelPosition.y+yYOffset);
                }
            }
        }
    }
    
    private void addListener(){
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0) {
                    CenterPanePopupMenu menu=new CenterPanePopupMenu(DataDrawPanel.this);
                    menu.show(DataDrawPanel.this, e.getX(), e.getY());
                }
            }
        });
    }
    
    public void update(){
        controller.update();
        this.repaint();
        
    }
}
