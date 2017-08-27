package cc.comac.ui.mainlayout;

import java.io.File;
import java.util.HashMap;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import cc.comac.controller.DrawPanelController;
import cc.comac.data.TargetDataPair;
import cc.comac.util.Context;

public class DefaultMainFrameCenterPane extends JTabbedPane {

    private HashMap<String, Boolean> drawNote=null;
    private HashMap<String, DrawPanelController> drawPanelController=null;
    
    public DefaultMainFrameCenterPane() {}

    public DefaultMainFrameCenterPane(JSplitPane mainPane) {
        drawNote=Context.getInstance().getDrawNote();
        drawPanelController=Context.getInstance().getDrawPanelController();
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    public void init(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair){
        DrawPanelController controller=new DrawPanelController(mainPane,targetLabelZipFilePath,pair);
        DataDrawPanel panel=new DataDrawPanel(targetLabelZipFilePath,controller);
        //change new Jpanel to drawPanel
        this.addTab(targetLabelZipFilePath.substring(targetLabelZipFilePath.lastIndexOf(File.separator)+1), panel);
        drawPanelController.put(targetLabelZipFilePath, controller);
        drawNote.put(targetLabelZipFilePath, true);
    }

    public void update(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair){
        DrawPanelController controller=drawPanelController.get(targetLabelZipFilePath);
        DataDrawPanel panel=new DataDrawPanel(targetLabelZipFilePath,controller);
        //change new Jpanel to drawPanel
        this.addTab(targetLabelZipFilePath.substring(targetLabelZipFilePath.lastIndexOf(File.separator)+1), panel);
        drawPanelController.put(targetLabelZipFilePath, controller);
        drawNote.put(targetLabelZipFilePath, true);
    }

}
