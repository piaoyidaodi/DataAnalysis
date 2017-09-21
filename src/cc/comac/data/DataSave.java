package cc.comac.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cc.comac.ui.mainlayout.DataDrawPanel;

public class DataSave implements Runnable{
    
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private File targetFile;
    private String labelName;
    
    public DataSave(DataDrawPanel panel,File targetFile) {
        this.timeLabelValue=panel.getController().getTimeLabelValue();
        this.dataLabelValue=panel.getController().getDataLabelValue();
        this.targetFile=targetFile;
        this.labelName=panel.getController().getTargetLabelZipFileName();
    }
    
    @Override
    public void run() {
        writeData();
    }

    private void writeData() {
        BufferedWriter writer=null;
        try {
            writer=new BufferedWriter(new FileWriter(targetFile));
            writer.write("time,"+labelName+"\n");
            for(int i=0;i<timeLabelValue.length;i++){
                writer.write(timeLabelValue[i]+","+String.valueOf(dataLabelValue[i])+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
