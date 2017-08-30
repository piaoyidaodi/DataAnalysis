package cc.comac.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import cc.comac.controller.WestPaneTreeController;

public class LabelDataProcessRunnable implements Runnable{
    
    private String targetLabelZipFilePath=null;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    private TargetDataPair targetDataPair=null;
    
    public LabelDataProcessRunnable() {}
    public LabelDataProcessRunnable(String targetLabelFilePath) {
        this.targetLabelZipFilePath=targetLabelFilePath;
    }
    
    @Override
    public void run() {
        init();
    }
    
    public String getTargetLabelZipFilePath() {
        return targetLabelZipFilePath;
    }
    public String[] getTimeLabelValue() {
        return timeLabelValue;
    }
    public Double[] getDataLabelValue() {
        return dataLabelValue;
    }

    public void init(){
        
        if (targetLabelZipFilePath==null) {
            //TODO pop out error
            return;
        }
        File targetLabelZipFile=new File(targetLabelZipFilePath);
        File targetLabelJBinFile=null;
        File targetTimeJBinFile=null;
        // UnZip Label
        targetLabelJBinFile=doUnZip(targetLabelZipFile);
        // UnZip Time 
        File targetParentDirFile=new File(targetLabelZipFile.getParent());
        File[] listFile=targetParentDirFile.listFiles();
        for (File file : listFile) {
            if (file.getName().toLowerCase().equals("time.zip")) {
                targetTimeJBinFile=doUnZip(file);
            }
        }
        readLabelData(targetLabelJBinFile);
        readTimeData(targetTimeJBinFile);
        targetDataPair=new TargetDataPair(targetLabelZipFilePath,timeLabelValue,dataLabelValue);
        WestPaneTreeController.getInstance().updateContext(targetDataPair);
        
    }
    private void readTimeData(File targetTimeJBinFile) {
        ObjectInputStream objIS=null;
        ArrayList<String> time=new ArrayList<>();
        try {
            objIS=new ObjectInputStream(new BufferedInputStream(new FileInputStream(targetTimeJBinFile)));
            time=(ArrayList<String>)objIS.readObject();
            timeLabelValue=time.toArray(new String[time.size()]);
            objIS.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void readLabelData(File targetLabelJBinFile) {
        ObjectInputStream objIS=null;
        ArrayList<Double> label=new ArrayList<>();
        try {
            objIS=new ObjectInputStream(new BufferedInputStream(new FileInputStream(targetLabelJBinFile)));
            label=(ArrayList<Double>)objIS.readObject();
            dataLabelValue=label.toArray(new Double[label.size()]);
            objIS.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File doUnZip(File targetLabelZipFile) {
        int length;
        final int BUFSIZE=4096;
        byte[] buf=new byte[BUFSIZE];
        String targetJBinFilePath=null;

        ZipInputStream ZIS=null;
        BufferedOutputStream BOS = null;
        try {
            ZIS = new ZipInputStream(new BufferedInputStream(new FileInputStream(targetLabelZipFile)));
            ZipEntry entry;
            while((entry=ZIS.getNextEntry())!=null){
                targetJBinFilePath=targetLabelZipFile.getParent()+File.separator+entry.getName();
                BOS=new BufferedOutputStream(new FileOutputStream(targetJBinFilePath));
                while ((length=ZIS.read(buf))!=-1) {
                    BOS.write(buf,0,length);
                }
            }
            ZIS.close();
            BOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(targetJBinFilePath);

    }

    public static void main(String args[]) {
        new LabelDataProcessRunnable("D:\\20170101\\FTPD-C919-10101-PD-170416-G-02-000FTE\\"
                + "FTPD-C919-10101-PD-170416-G-02-000FTE-429001-8\\ECU_RDIU15_50_00.zip").init();
        
    }
}
