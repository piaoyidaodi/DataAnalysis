package cc.comac.data;

import java.io.File;

import cc.comac.util.Context;

public class DataPreProcess {

    // set the fileChooser to only "txt" file
    private String workSpace=null;
    private String targetFile=null;
    
    public DataPreProcess(){}
    
    public void init(){
        
        boolean isProcessed=false;
        workSpace=Context.getInstance().getWorkSpace();
        targetFile=Context.getInstance().getTargetFile();
        
        if(targetFile!=null){
            File target=new File(targetFile);
            if (target.exists()) {
                File[] fileList=new File(target.getParent()).listFiles();
                for (File file : fileList) {
                    if (file.isDirectory()&&file.getName().equals(getNameNoPFix(target))) {
                        //TODO Preprocess has been done
                        isProcessed=true;
                        
                    }
                }
                if (!isProcessed) {
                    FileDataProcess fDataProcess=new FileDataProcess(targetFile);
                    fDataProcess.doProcess();
                }
                
            }
            
            
        }
        
        if (workSpace!=null) {
            WorkspaceDataProcess wDataProcess=new WorkspaceDataProcess(workSpace);
            wDataProcess.initWorkSpace();
            
        }
    }
    
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }
}
