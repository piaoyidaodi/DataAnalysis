package cc.comac.ui.mainlayout;

import java.awt.Dimension;

import javax.swing.JSplitPane;

public class MainFrameCenterPane extends DefaultMainFrameCenterPane {

    public MainFrameCenterPane() {}
    
    public MainFrameCenterPane(JSplitPane mainPane){
        super(mainPane);
        this.setPreferredSize(new Dimension(650, 600));
        this.setMinimumSize(new Dimension(650, 400));
    }
}
