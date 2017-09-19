package cc.comac.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import cc.comac.controller.DrawPanelController;
import cc.comac.ui.mainlayout.DataDrawPanel;
import cc.comac.util.DeviceProperty;

public class SettingPanelDialog extends JDialog {
    
    private DataDrawPanel panel;
    private DrawPanelController controller;
    private String[] timeLabelValue=null;
//    private Double[] dataLabelValue=null;
    private int timeIndexMin;
    private int timeIndexMax;
    private String timeStart;
    private String timeEnd;
    
    private int startHour;
    private int startMinute;
    private int startSecond;
    private int endHour;
    private int endMinute;
    private int endSecond;
    private float lineWidth;
    private Color lineColor;
    
    private DataPropertyPanel dataPropertyPanel=null;
    private CanvasPropertyPanel canvasPropertyPanel=null;
    private JPanel buttonPanel=null;
    private JButton okButton=null;
    private JButton cancelButton=null;
    
    public SettingPanelDialog() {
        super();
    }
    
    public SettingPanelDialog(JFrame owner,DataDrawPanel parent,String title,boolean modal){
        super(owner,title,modal);
        this.panel=(DataDrawPanel)parent;
        this.controller=panel.getController();
        this.timeLabelValue=controller.getTimeLabelValue();
//        this.dataLabelValue=controller.getDataLabelValue();
        this.setLayout(new BorderLayout());
        dataPropertyPanel=new DataPropertyPanel("Data Property");
        canvasPropertyPanel=new CanvasPropertyPanel("Canvas Property");
        
        buttonPanel=new JPanel();
        okButton=new JButton("OK");
        cancelButton=new JButton("Cancel");
        okButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingPanelDialog.this.updateCtrl();
                SettingPanelDialog.this.setVisible(false);
                panel.update();
                
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingPanelDialog.this.setVisible(false);
            }
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        
        this.add(dataPropertyPanel,BorderLayout.NORTH);
        this.add(canvasPropertyPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);

        this.setLocation((int)DeviceProperty.getDeviceWidth()/3, (int)DeviceProperty.getDeviceHeight()/3);
        
        updatedata();
    }

    public void updatedata() {
        String[] timeStartString;
        String[] timeEndString;
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();
        timeStart=timeLabelValue[timeIndexMin];
        timeEnd=timeLabelValue[timeIndexMax];
        timeStartString=timeStart.split(":");
        timeEndString=timeEnd.split(":");
        
        startHour=Integer.parseInt(timeStartString[0]);
        startMinute=Integer.parseInt(timeStartString[1]);
        startSecond=Integer.parseInt(timeStartString[2]);
        endHour=Integer.parseInt(timeEndString[0]);
        endMinute=Integer.parseInt(timeEndString[1]);
        endSecond=Integer.parseInt(timeEndString[2]);
        lineWidth=controller.getLineWidth();
        lineColor=controller.getLineColor();
        
        dataPropertyPanel.setStartHour(startHour);
        dataPropertyPanel.setStartMinute(startMinute);
        dataPropertyPanel.setStartSecond(startSecond);
        dataPropertyPanel.setEndHour(endHour);
        dataPropertyPanel.setEndMinute(endMinute);
        dataPropertyPanel.setEndSecond(endSecond);
        dataPropertyPanel.setLineWidth(lineWidth);
        dataPropertyPanel.setLineColor(lineColor);
        
        this.pack();
    }
    
    private void updateCtrl(){
        String timeStartString=dataPropertyPanel.getStartHour()+":"+dataPropertyPanel.getStartMinute()+":"+dataPropertyPanel.getStartSecond();
        String timeEndString=dataPropertyPanel.getEndHour()+":"+dataPropertyPanel.getEndMinute()+":"+dataPropertyPanel.getEndSecond();
        System.out.println(timeStartString);
        System.out.println(timeEndString);
        for(int i=0;i<timeLabelValue.length;i++){
            if (timeLabelValue[i].contains(timeStartString)) {
                controller.setTimeIndexMin(i);
                System.out.println(timeLabelValue[i]);
                break;
            }
        }
        for(int i=timeLabelValue.length-1;i>=0;i--){
            if (timeLabelValue[i].contains(timeEndString)) {
                controller.setTimeIndexMax(i);
                System.out.println(timeLabelValue[i]);
                break;
            }
        }
        controller.setLineWidth(dataPropertyPanel.getLineWidth());
        controller.setLineColor(dataPropertyPanel.getLineColor());
        controller.setLabelNameFontColor(dataPropertyPanel.getLineColor());
    }
}

class DataPropertyPanel extends JPanel{
    private JPanel startTimePanel=null;
    private JSpinner startHour=null;
    private JSpinner startMinute=null;
    private JSpinner startSecond=null;
    
    private JPanel endTimePanel=null;
    private JSpinner endHour=null;
    private JSpinner endMinute=null;
    private JSpinner endSecond=null;
    
    private JPanel linePropertyPanel=null;
    private JSpinner lineWidth=null;
    private JButton lineColorButton=null;

    public DataPropertyPanel() {}

    public DataPropertyPanel(String title) {
        startTimePanel=new JPanel();
        endTimePanel=new JPanel();
        linePropertyPanel=new JPanel();
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(1, 3));
        
        initStartTimePanel();
        initEndTimePanel();
        initLinePropertyPanel();
        this.add(startTimePanel);
        this.add(endTimePanel);
        this.add(linePropertyPanel);
    }

    private void initStartTimePanel() {
        startTimePanel.setLayout(new GridLayout(3, 2));
        startTimePanel.setBorder(BorderFactory.createTitledBorder("Start Time"));
        startHour=new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        startMinute=new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        startSecond=new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        
        startTimePanel.add(new JLabel("Hour:"));
        startTimePanel.add(startHour);
        startTimePanel.add(new JLabel("Minute:"));
        startTimePanel.add(startMinute);
        startTimePanel.add(new JLabel("Second:"));
        startTimePanel.add(startSecond);
    }

    private void initEndTimePanel() {
        endTimePanel.setLayout(new GridLayout(3, 2));
        endTimePanel.setBorder(BorderFactory.createTitledBorder("End Time"));
        endHour=new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        endMinute=new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        endSecond=new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        
        endTimePanel.add(new JLabel("Hour:"));
        endTimePanel.add(endHour);
        endTimePanel.add(new JLabel("Minute:"));
        endTimePanel.add(endMinute);
        endTimePanel.add(new JLabel("Second:"));
        endTimePanel.add(endSecond);
    }
    
    private void initLinePropertyPanel() {
        linePropertyPanel.setLayout(new GridLayout(3, 2));
        linePropertyPanel.setBorder(BorderFactory.createTitledBorder("Line Property"));
        lineWidth=new JSpinner(new SpinnerNumberModel(1.0, 1.0, 3.0, 0.5));
        lineColorButton=new JButton();
        //TODO background color
        lineColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lineColorButton.setBackground(JColorChooser.showDialog(null, "Choose Line Color", lineColorButton.getBackground()));
            }
        });
        lineColorButton.setBackground(Color.red);
        
        linePropertyPanel.add(new JLabel("Line Width:"));
        linePropertyPanel.add(lineWidth);
        linePropertyPanel.add(new JLabel("Line Color:"));
        linePropertyPanel.add(lineColorButton);
    }
    
    public int getStartHour() {
        return (int)startHour.getValue();
    }

    public void setStartHour(int startHour) {
        this.startHour.setValue(startHour);
    }

    public int getStartMinute() {
        return (int)startMinute.getValue();
    }

    public void setStartMinute(int startMinute) {
        this.startMinute.setValue(startMinute);
    }

    public int getStartSecond() {
        return (int)startSecond.getValue();
    }

    public void setStartSecond(int startSecond) {
        this.startSecond.setValue(startSecond);
    }

    public int getEndHour() {
        return (int)endHour.getValue();
    }

    public void setEndHour(int endHour) {
        this.endHour.setValue(endHour);
    }

    public int getEndMinute() {
        return (int)endMinute.getValue();
    }

    public void setEndMinute(int endMinute) {
        this.endMinute.setValue(endMinute);
    }

    public int getEndSecond() {
        return (int)endSecond.getValue();
    }

    public void setEndSecond(int endSecond) {
        this.endSecond.setValue(endSecond);
    }

    public float getLineWidth() {
        return (float)lineWidth.getValue();
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth.setValue(lineWidth);
    }

    public Color getLineColor() {
        return lineColorButton.getBackground();
    }

    public void setLineColor(Color lineColor) {
        this.lineColorButton.setBackground(lineColor);
    }

}

class CanvasPropertyPanel extends JPanel{
    public CanvasPropertyPanel() {}

    public CanvasPropertyPanel(String title) {
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(1, 3));
//        this.addPanel("LableName");
//        this.addPanel("BaseColors");
//        this.addPanel("Canvas");
//        this.addPanel("Coordinate");
    }
}

