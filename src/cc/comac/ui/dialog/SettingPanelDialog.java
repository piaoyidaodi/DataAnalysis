package cc.comac.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
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
    private String lableName;
    
    private DataPropertyPanel dataPropertyPanel=null;
    private CanvasPropertyPanel canvasPropertyPanel=null;
    
    private JPanel buttonPanel=null;
    private JButton okButton=null;
    private JButton cancelButton=null;
    private JButton resetButton=null;
    
    public SettingPanelDialog() {
        super();
    }
    
    public SettingPanelDialog(JFrame owner,DataDrawPanel parent,String title,boolean modal){
        super(owner,title,modal);
        this.panel=(DataDrawPanel)parent;
        this.controller=panel.getController();
        this.timeLabelValue=controller.getTimeLabelValue();
        this.setLayout(new BorderLayout());
        dataPropertyPanel=new DataPropertyPanel("Data Property",controller);
        canvasPropertyPanel=new CanvasPropertyPanel("Canvas Property");
        
        buttonPanel=new JPanel();
        okButton=new JButton("OK");
        cancelButton=new JButton("Cancel");
        resetButton=new JButton("Reset");
        okButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        SettingPanelDialog.this.updateCtrl();
                        SettingPanelDialog.this.setVisible(false);
                        panel.update();
                    }
                });
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        SettingPanelDialog.this.setVisible(false);
                    }
                });
            }
        });
        resetButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        controller.reset();
                        SettingPanelDialog.this.updatedata();
                    }
                });
            }
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(resetButton);
        
        this.add(dataPropertyPanel,BorderLayout.NORTH);
        this.add(canvasPropertyPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);

        this.setLocation((int)DeviceProperty.getDeviceWidth()/3, (int)DeviceProperty.getDeviceHeight()/3);        
        this.setSize((int)DeviceProperty.getDeviceWidth()*3/5, (int)DeviceProperty.getDeviceHeight()*2/5);
        this.setResizable(false);
        
        updatedata();
    }

    public void updatedata() {
        String[] timeStartString;
        String[] timeEndString;
        this.timeIndexMin=controller.getTimeIndexMin();
        this.timeIndexMax=controller.getTimeIndexMax();
        this.timeStart=timeLabelValue[timeIndexMin];
        this.timeEnd=timeLabelValue[timeIndexMax];
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
        lableName=controller.getLabelName();
        
        dataPropertyPanel.setStartHour(startHour);
        dataPropertyPanel.setStartMinute(startMinute);
        dataPropertyPanel.setStartSecond(startSecond);
        dataPropertyPanel.setEndHour(endHour);
        dataPropertyPanel.setEndMinute(endMinute);
        dataPropertyPanel.setEndSecond(endSecond);
        dataPropertyPanel.setLineWidth(lineWidth);
        dataPropertyPanel.setLineColor(lineColor);
        dataPropertyPanel.setLabelName(lableName);
        
        canvasPropertyPanel.setLeftPadding(controller.getCanvasSpecialYOffset());
        canvasPropertyPanel.setBottomPadding(controller.getCanvasSpecialXOffset());
        canvasPropertyPanel.setGeneralPadding(controller.getCanvasGeneralOffset());
        canvasPropertyPanel.setAxisFontName(controller.getFontName());
        canvasPropertyPanel.setAxisFontSize(controller.getFontSize());
        canvasPropertyPanel.setLabelFontName(controller.getLabelNameFontName());
        canvasPropertyPanel.setLabelFontSize(controller.getLabelNameFontSize());
        canvasPropertyPanel.setPanelBGColor(controller.getPanelBGColor());
        canvasPropertyPanel.setCanvasBGColor(controller.getCanvasBGColor());
        canvasPropertyPanel.setCoordinateColor(controller.getCoordinateColor());
        canvasPropertyPanel.setScaleLineColor(controller.getScaleLineColor());
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
        controller.setLabelName(dataPropertyPanel.getLabelName());
        controller.setLabelNameFontColor(dataPropertyPanel.getLineColor());
        
        controller.setCanvasSpecialYOffset(canvasPropertyPanel.getLeftPadding());
        controller.setCanvasSpecialXOffset(canvasPropertyPanel.getBottomPadding());
        controller.setCanvasGeneralOffset(canvasPropertyPanel.getGeneralPadding());
        controller.setFontName(canvasPropertyPanel.getAxisFontName());
        controller.setFontSize(canvasPropertyPanel.getAxisFontSize());
        controller.setLabelNameFontName(canvasPropertyPanel.getLabelFontName());
        controller.setLabelNameFontSize(canvasPropertyPanel.getLabelFontSize());
        controller.setPanelBGColor(canvasPropertyPanel.getPanelBGColor());
        controller.setCanvasBGColor(canvasPropertyPanel.getCanvasBGColor());
        controller.setCoordinateColor(canvasPropertyPanel.getCanvasBGColor());
        controller.setScaleLineColor(canvasPropertyPanel.getScaleLineColor());
    }
}

class DataPropertyPanel extends JPanel{
    private DrawPanelController controller;
    
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
    private JTextField labelNameTxtField=null;

    public DataPropertyPanel() {}

    public DataPropertyPanel(String title,DrawPanelController controller) {
        this.controller=controller;
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
        labelNameTxtField=new JTextField();
        lineColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        lineColorButton.setBackground(JColorChooser.showDialog(null, "Choose Line Color", lineColorButton.getBackground()));
                    }
                });
            }
        });
        lineColorButton.setBackground(controller.getLineColor());
        labelNameTxtField.setText(controller.getLabelName());
        
        linePropertyPanel.add(new JLabel("Line Width:"));
        linePropertyPanel.add(lineWidth);
        linePropertyPanel.add(new JLabel("Line Color:"));
        linePropertyPanel.add(lineColorButton);
        linePropertyPanel.add(new JLabel("Label Name:"));
        linePropertyPanel.add(labelNameTxtField);
    }
    
    public String getStartHour() {
        String tmp=String.valueOf(startHour.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setStartHour(int startHour) {
        this.startHour.setValue(startHour);
    }

    public String getStartMinute() {
        String tmp=String.valueOf(startMinute.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute.setValue(startMinute);
    }

    public String getStartSecond() {
        String tmp=String.valueOf(startSecond.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setStartSecond(int startSecond) {
        this.startSecond.setValue(startSecond);
    }

    public String getEndHour() {
        String tmp=String.valueOf(endHour.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setEndHour(int endHour) {
        this.endHour.setValue(endHour);
    }

    public String getEndMinute() {
        String tmp=String.valueOf(endMinute.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute.setValue(endMinute);
    }

    public String getEndSecond() {
        String tmp=String.valueOf(endSecond.getValue());
        return tmp.length()==1?("0"+tmp):tmp;
    }

    public void setEndSecond(int endSecond) {
        this.endSecond.setValue(endSecond);
    }

    public float getLineWidth() {
        return Float.parseFloat(String.valueOf(lineWidth.getValue()));
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

    public String getLabelName() {
        return labelNameTxtField.getText().length()>15?labelNameTxtField.getText().substring(0,15):labelNameTxtField.getText();
    }

    public void setLabelName(String labelName) {
        this.labelNameTxtField.setText(labelName);
    }

}

class CanvasPropertyPanel extends JPanel{
    private JPanel canvasPaddingPanel=null;
    private JSpinner leftPadding=null;
    private JSpinner bottomPadding=null;
    private JSpinner generalPadding=null;
    
    private JPanel fontPropertyPanel=null;
    private JComboBox<String> axisFontName=null;
    private JSpinner axisFontSize=null;
    private JComboBox<String> labelFontName=null;
    private JSpinner labelFontSize=null;
    
    private JPanel colorPropertyPanel=null;
    private JButton panelBGColorButton=null;
    private JButton canvasBGColorButton=null;
    private JButton coordinateColorButton=null;
    private JButton scaleLineColorButton=null;

    public CanvasPropertyPanel() {}

    public CanvasPropertyPanel(String title) {
        canvasPaddingPanel=new JPanel();
        fontPropertyPanel=new JPanel();
        colorPropertyPanel=new JPanel();
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(1, 3));
        
        initCanvasPaddingPanel();
        initFontPropertyPanel();
        initColorPropertyPanel();
        this.add(canvasPaddingPanel);
        this.add(fontPropertyPanel);
        this.add(colorPropertyPanel);
    }

    private void initCanvasPaddingPanel() {
        canvasPaddingPanel.setLayout(new GridLayout(3, 2));
        canvasPaddingPanel.setBorder(BorderFactory.createTitledBorder("Canvas Padding"));
        leftPadding=new JSpinner(new SpinnerNumberModel(50, 10, 100, 1));
        bottomPadding=new JSpinner(new SpinnerNumberModel(35, 10, 50, 1));
        generalPadding=new JSpinner(new SpinnerNumberModel(10, 0, 30, 1));
        
        canvasPaddingPanel.add(new JLabel("LeftPadding:"));
        canvasPaddingPanel.add(leftPadding);
        canvasPaddingPanel.add(new JLabel("BottomPadding:"));
        canvasPaddingPanel.add(bottomPadding);
        canvasPaddingPanel.add(new JLabel("GeneralPadding:"));
        canvasPaddingPanel.add(generalPadding);
    }

    private void initFontPropertyPanel() {
        fontPropertyPanel.setLayout(new GridLayout(4, 2));
        fontPropertyPanel.setBorder(BorderFactory.createTitledBorder("Font Property"));
        String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        axisFontName=new JComboBox<>(fontNames);
        axisFontSize=new JSpinner(new SpinnerNumberModel(12, 8, 20, 1));
        labelFontName=new JComboBox<>(fontNames);
        labelFontSize=new JSpinner(new SpinnerNumberModel(12, 8, 20, 1));
        
        fontPropertyPanel.add(new JLabel("AxisFontName"));
        fontPropertyPanel.add(axisFontName);
        fontPropertyPanel.add(new JLabel("AxisFontSize"));
        fontPropertyPanel.add(axisFontSize);
        fontPropertyPanel.add(new JLabel("LabelFontName"));
        fontPropertyPanel.add(labelFontName);
        fontPropertyPanel.add(new JLabel("LabelFontSize"));
        fontPropertyPanel.add(labelFontSize);
    }

    private void initColorPropertyPanel() {
        colorPropertyPanel.setLayout(new GridLayout(4, 2));
        colorPropertyPanel.setBorder(BorderFactory.createTitledBorder("Color Property"));
        panelBGColorButton=new JButton();
        panelBGColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        panelBGColorButton.setBackground(JColorChooser.showDialog(null, "Choose Panel Color", panelBGColorButton.getBackground()));
                    }
                });
            }
        });
        canvasBGColorButton=new JButton();
        canvasBGColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        canvasBGColorButton.setBackground(JColorChooser.showDialog(null, "Choose Canvas Color", canvasBGColorButton.getBackground()));
                    }
                });
            }
        });
        coordinateColorButton=new JButton();
        coordinateColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        coordinateColorButton.setBackground(JColorChooser.showDialog(null, "Choose Axis Color", coordinateColorButton.getBackground()));
                    }
                });
            }
        });
        scaleLineColorButton=new JButton();
        scaleLineColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        scaleLineColorButton.setBackground(JColorChooser.showDialog(null, "Choose Scale Color", scaleLineColorButton.getBackground()));
                    }
                });
            }
        });
        
        colorPropertyPanel.add(new Label("Panel Color"));
        colorPropertyPanel.add(panelBGColorButton);
        colorPropertyPanel.add(new Label("Canvas Color"));
        colorPropertyPanel.add(canvasBGColorButton);
        colorPropertyPanel.add(new Label("Axis Color"));
        colorPropertyPanel.add(coordinateColorButton);
        colorPropertyPanel.add(new Label("Scale Color"));
        colorPropertyPanel.add(scaleLineColorButton);
    }

    public int getLeftPadding() {
        return (int)leftPadding.getValue();
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding.setValue(leftPadding);
    }

    public int getBottomPadding() {
        return (int)bottomPadding.getValue();
    }

    public void setBottomPadding(int bottomPadding) {
        this.bottomPadding.setValue(bottomPadding);
    }

    public int getGeneralPadding() {
        return (int)generalPadding.getValue();
    }

    public void setGeneralPadding(int generalPadding) {
        this.generalPadding.setValue(generalPadding);
    }

    public String getAxisFontName() {
        return axisFontName.getItemAt(axisFontName.getSelectedIndex());
    }

    public void setAxisFontName(String axisFontName) {
        this.axisFontName.setSelectedItem(axisFontName);
    }

    public int getAxisFontSize() {
        return (int)axisFontSize.getValue();
    }

    public void setAxisFontSize(int axisFontSize) {
        this.axisFontSize.setValue(axisFontSize);
    }

    public String getLabelFontName() {
        return labelFontName.getItemAt(labelFontName.getSelectedIndex());
    }

    public void setLabelFontName(String labelFontName) {
        this.labelFontName.setSelectedItem(labelFontName);
    }

    public int getLabelFontSize() {
        return (int)labelFontSize.getValue();
    }

    public void setLabelFontSize(int labelFontSize) {
        this.labelFontSize.setValue(labelFontSize);
    }

    public Color getPanelBGColor() {
        return panelBGColorButton.getBackground();
    }

    public void setPanelBGColor(Color panelBGColorButton) {
        this.panelBGColorButton.setBackground(panelBGColorButton);
    }

    public Color getCanvasBGColor() {
        return canvasBGColorButton.getBackground();
    }

    public void setCanvasBGColor(Color canvasBGColorButton) {
        this.canvasBGColorButton.setBackground(canvasBGColorButton);
    }

    public Color getCoordinateColor() {
        return coordinateColorButton.getBackground();
    }

    public void setCoordinateColor(Color coordinateColorButton) {
        this.coordinateColorButton.setBackground(coordinateColorButton);
    }

    public Color getScaleLineColor() {
        return scaleLineColorButton.getBackground();
    }

    public void setScaleLineColor(Color scaleLineColorButton) {
        this.scaleLineColorButton.setBackground(scaleLineColorButton);
    }
    
}

