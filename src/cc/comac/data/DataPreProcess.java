package cc.comac.data;

import java.io.File;

import cc.comac.controller.WestPaneTreeController;

public class DataPreProcess {

    // set the fileChooser to only "txt" file

    
    public DataPreProcess(){}
    
    public void initFile(File targetFile){
        
        boolean isProcessed=false;
        
        System.out.println(targetFile.getAbsoluteFile());
        
        File[] fileList=new File(targetFile.getParent()).listFiles();
        for (File file : fileList) {
            if (file.isDirectory()&&file.getName().equals(getNameNoPFix(targetFile))) {
                isProcessed=true;
                System.out.println(targetFile.getAbsolutePath()+" File Processed");
                WestPaneTreeController.getInstance().updateUI();
            }
        }
        if (!isProcessed) {
            FileDataProcessRunnable fDataProcessRunnable=new FileDataProcessRunnable(targetFile.getAbsolutePath());
            new Thread(fDataProcessRunnable).start();
        }
        
    }
    
    public void initDirectory(File workSpaceFile){
        boolean isProcessed=false;
        
        System.out.println(workSpaceFile.getAbsolutePath());
        
        
        File[] fileList=workSpaceFile.listFiles();
        for (File file : fileList) {
            if (file.getName().equals("markFile.md")) {
                isProcessed=true;
                System.out.println(workSpaceFile.getAbsolutePath()+" Directory Processed");
                WestPaneTreeController.getInstance().updateUI();
            }
        }
        if (!isProcessed) {
            WorkspaceDataProcessRunnable wDataProcessRunnable=new WorkspaceDataProcessRunnable(workSpaceFile.getAbsolutePath());
            new Thread(wDataProcessRunnable).start();
        }
        
    }

    
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }
}
