package cc.comac.util;

public class Context {

    private boolean firstUse;
    private String theme;
    private String workSpace;
    private String targetFile;
    
    private Context(){}
    
    private static final Context instance=new Context();
    
    public static Context getInstance() {
        
        return instance;
    }
    
    public boolean isFirstUse() {
        return firstUse;
    }

    public void setFirstUse(boolean firstUse) {
        this.firstUse = firstUse;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
//        System.out.println(theme);
        
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }
    
    public void initial(){
        
    }
    
}
