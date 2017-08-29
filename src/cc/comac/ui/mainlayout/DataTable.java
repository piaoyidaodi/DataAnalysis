package cc.comac.ui.mainlayout;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import cc.comac.controller.DrawPanelController;

public class DataTable extends JTable {

    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private DrawPanelController[] controllers=null;
    
    public DataTable(DataSplitPane parent,DrawPanelController... controllers) {
        super();
        this.controllers=controllers;
        init();
    }
    private void init(){

        this.timeLabelValue=controllers[0].getTimeLabelValue();
        this.dataLabelValue=controllers[0].getDataLabelValue();

        TableModel tableModel=new AbstractTableModel() {
            
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if (columnIndex==0) {
                    return rowIndex+1;
                }else if (columnIndex==1){
                    return timeLabelValue[rowIndex];
                }else{
                    Double[] labelValue=controllers[columnIndex-2].getDataLabelValue();
                    return labelValue[rowIndex];
                }
            }
            
            @Override
            public int getRowCount() {
                return timeLabelValue.length>=dataLabelValue.length?timeLabelValue.length:dataLabelValue.length;
            }
            
            @Override
            public int getColumnCount() {
                return controllers.length+2;
            }
            
            @Override
            public String getColumnName(int column) {
                if (column==0) {
                    return "Index";
                }else if (column==1) {
                    return "Time";
                }else{
                    return controllers[column-2].getTargetLabelZipFileName();
                }
            }
        };
        
        this.setModel(tableModel);
        
    }
}
