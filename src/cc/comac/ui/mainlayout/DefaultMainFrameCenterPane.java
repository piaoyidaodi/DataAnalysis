package cc.comac.ui.mainlayout;

import java.io.File;
import java.util.HashMap;

import javax.swing.JScrollPane;
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
        initUI(mainPane,targetLabelZipFilePath,controller,pair);
        
    }

    public void update(JSplitPane mainPane,String targetLabelZipFilePath,TargetDataPair pair){
        DrawPanelController controller=drawPanelController.get(targetLabelZipFilePath);
        initUI(mainPane,targetLabelZipFilePath,controller,pair);
    }
    
    private void initUI(JSplitPane mainPane,String targetLabelZipFilePath,DrawPanelController controller,TargetDataPair pair){
        DataSplitPane dataSplitPane=new DataSplitPane();
        
        DataDrawPanel datapanel=new DataDrawPanel(dataSplitPane,targetLabelZipFilePath,controller);
        
        DataTable datatable=new DataTable(dataSplitPane,controller);
        JScrollPane scrollPane=new JScrollPane(datatable);
        
        dataSplitPane.add(datapanel);
        dataSplitPane.add(scrollPane);
        dataSplitPane.resetToPreferredSizes();
        
        
        //change new Jpanel to drawPanel
        this.addTab(targetLabelZipFilePath.substring(targetLabelZipFilePath.lastIndexOf(File.separator)+1), dataSplitPane);
        drawPanelController.put(targetLabelZipFilePath, controller);
        drawNote.put(targetLabelZipFilePath, true);
        Context.getInstance().getMainFrame().pack();
        
    }

}
