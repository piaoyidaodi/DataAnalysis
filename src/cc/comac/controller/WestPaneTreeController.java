package cc.comac.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import cc.comac.data.DataPreProcess;
import cc.comac.data.LabelDataProcessRunnable;
import cc.comac.data.TargetDataPair;
import cc.comac.util.Context;

public class WestPaneTreeController{
    
    private HashSet<String> workSpaceTreeHashSet=null;
    private HashSet<String> targetZipFileSet=null;
    private HashMap<String, Boolean> drawNote=null;
    private ArrayList<TargetDataPair> targetDataPairs=null;
    private String targetLabelZipFilePath=null;
    
    private static final WestPaneTreeController instance=new WestPaneTreeController();
    private WestPaneTreeController() {}
    
    public static WestPaneTreeController getInstance() {
        return instance;
    }

    public void updateUI() {
        Context.getInstance().getMainFrameWestPane().update(Context.getInstance().getMainPane());
    }

    public void updateDataModel() {
        
        workSpaceTreeHashSet=Context.getInstance().getWorkSpaceTreeSet();
        Iterator<String>iterator= workSpaceTreeHashSet.iterator();
        while (iterator.hasNext()){
            String fileName=iterator.next();
//            System.out.println("do File "+fileName);

            File file=new File(fileName);
            if (!file.exists()) return;
            DataPreProcess dataPreProcess=new DataPreProcess();

            if (file.isDirectory()) {
                dataPreProcess.initDirectory(file);
            } else {
                dataPreProcess.initFile(file);
            }
        }
    }
    
    public void updateLabelFileModel(){
        targetLabelZipFilePath=Context.getInstance().getTargetLabelZipFilePath();
        targetZipFileSet=Context.getInstance().getTargetZipFileSet();
        drawNote=Context.getInstance().getDrawNote();
        targetDataPairs=Context.getInstance().getTargetDataPairs();
        
        if (targetZipFileSet.contains(targetLabelZipFilePath)) {
            Iterator<TargetDataPair> iterator=targetDataPairs.iterator();
            while (iterator.hasNext()) {
                TargetDataPair pair=iterator.next();
                if (pair.getTargetLabelZipFilePath().equals(targetLabelZipFilePath)) {
                    drawOldData(targetLabelZipFilePath, pair);
                }
            }
        }else {
            LabelDataProcessRunnable labelDataProcess=new LabelDataProcessRunnable(targetLabelZipFilePath);
            targetZipFileSet.add(targetLabelZipFilePath);
            new Thread(labelDataProcess).start();
        }
    }

    public void updateContext(TargetDataPair pair){
        ArrayList<TargetDataPair> arrayList=Context.getInstance().getTargetDataPairs();
        arrayList.add(pair);
//        System.out.println(arrayList.toString());
        //TODO New Data not in the Note
        drawRawData(pair.getTargetLabelZipFilePath(),pair);
    }
    
    public void drawRawData(String targetLabelZipFilePath,TargetDataPair pair){
        CenterPaneController.getInstance().drawRawData(pair.getTargetLabelZipFilePath(),pair);
    }

    public void drawOldData(String targetLabelZipFilePath2, TargetDataPair pair) {        
        CenterPaneController.getInstance().drawOldData(pair.getTargetLabelZipFilePath(),pair);
    }

}
