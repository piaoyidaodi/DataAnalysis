package cc.comac.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JSplitPane;

import cc.comac.controller.DrawPanelController;
import cc.comac.data.TargetDataPair;
import cc.comac.ui.MainFrame;
import cc.comac.ui.mainlayout.MainFrameCenterPane;
import cc.comac.ui.mainlayout.MainFrameWestPane;
import cc.comac.ui.menu.MainFrameMenu;
import cc.comac.ui.toolbar.MainFrameToolbar;

public class Context {

    private boolean firstUse;
    private String theme=null;
    private String workSpace=null;
    private String targetLabelZipFilePath=null;
    private MainFrame mainFrame=null;
    private MainFrameMenu mainFrameMenu=null;
    private MainFrameToolbar mainFrameToolbar=null;
    private JSplitPane mainSplitPane=null;
    private MainFrameCenterPane mainFrameCenterPane=null;
    private MainFrameWestPane mainFrameWestPane=null;
    private JSplitPane mainPane=null;
    private final int SETSIZE=5;
    
    private HashSet<String> workSpaceTreeHashSet=new HashSet<>();
    private HashSet<String> targetZipFileSet=new HashSet<>();
    private ArrayList<TargetDataPair> targetDataPairs=new ArrayList<>();
    private HashMap<String, Boolean> drawNote=new HashMap<>();
    private HashMap<String, DrawPanelController> drawPanelController=new HashMap<>();

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

    public String getTargetDir() {
        if (workSpace==null) workSpace=DeviceProperty.getDefaultWkDir();
        return workSpace;
    }

    public void setTarget(String workSpace) {
        this.workSpace = workSpace;
        File workSpaceFile=new File(workSpace);
        if (workSpaceFile.exists()&&workSpaceFile.isDirectory()){
            if (!(workSpaceFile.getName().matches("\\d{8}"))) {
                return;
            }
        }
        if (workSpaceTreeHashSet.size()==SETSIZE) {
            workSpaceTreeHashSet.remove(workSpaceTreeHashSet.iterator().next());
            workSpaceTreeHashSet.add(workSpace);
        }else{
            workSpaceTreeHashSet.add(workSpace);
        }
        
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    
    public HashSet<String> getWorkSpaceTreeSet() {
        return workSpaceTreeHashSet;
    }

    public void initial(){
        
    }
    
    public MainFrameMenu getMainFrameMenu() {
        return mainFrameMenu;
    }

    public void setMainFrameMenu(MainFrameMenu mainFrameMenu) {
        this.mainFrameMenu = mainFrameMenu;
    }

    public MainFrameToolbar getMainFrameToolbar() {
        return mainFrameToolbar;
    }

    public void setMainFrameToolbar(MainFrameToolbar mainFrameToolbar) {
        this.mainFrameToolbar = mainFrameToolbar;
    }

    public MainFrameCenterPane getMainFrameCenterPane() {
        return mainFrameCenterPane;
    }

    public void setMainFrameCenterPane(MainFrameCenterPane mainFrameCenterPane) {
        this.mainFrameCenterPane = mainFrameCenterPane;
    }

    public MainFrameWestPane getMainFrameWestPane() {
        return mainFrameWestPane;
    }

    public void setMainFrameWestPane(MainFrameWestPane mainFrameWestPane) {
        this.mainFrameWestPane = mainFrameWestPane;
    }

    public JSplitPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(JSplitPane mainPane) {
        this.mainPane = mainPane;
    }

    public String getTargetLabelZipFilePath() {
        return targetLabelZipFilePath;
    }

    public void setTargetLabelZipFilePath(String targetLabel) {
        this.targetLabelZipFilePath = targetLabel;
    }

    public ArrayList<TargetDataPair> getTargetDataPairs() {
        return targetDataPairs;
    }

    public void setTargetDataPairs(ArrayList<TargetDataPair> targetDataPairs) {
        this.targetDataPairs = targetDataPairs;
    }
    
    public HashSet<String> getTargetZipFileSet() {
        return targetZipFileSet;
    }

    public void setTargetZipFileSet(HashSet<String> targetZipFileSet) {
        this.targetZipFileSet = targetZipFileSet;
    }

    public HashMap<String, Boolean> getDrawNote() {
        return drawNote;
    }

    public HashMap<String, DrawPanelController> getDrawPanelController() {
        return drawPanelController;
    }

    public JSplitPane getMainSplitPane() {
        return mainSplitPane;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

}
