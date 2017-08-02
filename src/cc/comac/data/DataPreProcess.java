package cc.comac.data;

import cc.comac.util.Context;

public class DataPreProcess {

    private String workSpace=null;
    private String targetFile=null;
    
    public DataPreProcess(){}
    
    public void init(){
        
        workSpace=Context.getInstance().getWorkSpace();
        targetFile=Context.getInstance().getTargetFile();
        
        if(targetFile!=null){
            FileDataProcess fDataProcess=new FileDataProcess(targetFile);
            fDataProcess.doProcess();
        }
        if (workSpace!=null) {
            
        }
    }
}
