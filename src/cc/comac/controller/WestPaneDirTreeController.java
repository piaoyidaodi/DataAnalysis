package cc.comac.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import cc.comac.data.DataPreProcess;
import cc.comac.data.LabelDataProcessRunnable;
import cc.comac.data.TargetDataPair;
import cc.comac.util.Context;

public class WestPaneDirTreeController implements Controller{
    
    private HashSet<String> workSpaceTreeHashSet=null;

    
    private static final WestPaneDirTreeController instance=new WestPaneDirTreeController();
    private WestPaneDirTreeController() {}
    
    public static WestPaneDirTreeController getInstance() {
        return instance;
    }

    @Override
    public void updateUI() {
        Context.getInstance().getMainFrameWestPane().update(Context.getInstance().getMainPane());
    }

    @Override
    public void updateDataModel() {
        
        workSpaceTreeHashSet=Context.getInstance().getWorkSpaceTreeSet();
        Iterator<String>iterator= workSpaceTreeHashSet.iterator();
        while (iterator.hasNext()){
            String fileName=iterator.next();
            System.out.println("do File "+fileName);

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
        String targetLabelZipFilePath=Context.getInstance().getTargetLabel();
        LabelDataProcessRunnable labelDataProcess=new LabelDataProcessRunnable(targetLabelZipFilePath);
        new Thread(labelDataProcess).start();
                
    }
    
    public void updateContext(TargetDataPair pair){
        ArrayList<TargetDataPair> arrayList=Context.getInstance().getTargetDataPairs();
        arrayList.add(pair);
        arrayList.toString();
    }

}
