package cc.comac.controller;

import java.util.ArrayList;
import java.util.Iterator;

import cc.comac.data.TargetDataPair;
import cc.comac.ui.mainlayout.MainFrameCenterPane;
import cc.comac.util.Context;

public class CenterPaneController {
    
    private ArrayList<TargetDataPair> targetDataPairs=Context.getInstance().getTargetDataPairs();
    
    private static final CenterPaneController instance=new CenterPaneController();
    
    private CenterPaneController(){}
    
    public static CenterPaneController getInstance() {
        return instance;
    }

    public void drawRawData(String targetLabelZipFilePath,TargetDataPair pair) {
        MainFrameCenterPane mainFrameCenterPane=Context.getInstance().getMainFrameCenterPane();
        mainFrameCenterPane.init(Context.getInstance().getMainPane(),targetLabelZipFilePath,pair);
    }
    
    public void drawAll(){
        Iterator<TargetDataPair> iterator=targetDataPairs.iterator();
        while (iterator.hasNext()) {
            TargetDataPair pair=iterator.next();
            drawRawData(pair.getTargetLabelZipFilePath(),iterator.next());
        }
    }

    public void drawOldData(String targetLabelZipFilePath, TargetDataPair pair) {
        MainFrameCenterPane mainFrameCenterPane=Context.getInstance().getMainFrameCenterPane();
        mainFrameCenterPane.update(Context.getInstance().getMainPane(),targetLabelZipFilePath,pair);
    }

}
