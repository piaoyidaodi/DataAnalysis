package cc.comac.ui.mainlayout;

import java.awt.Dimension;

import javax.swing.JSplitPane;

public class MainFrameWestPane extends DefaultMainFrameWestPane {

    public MainFrameWestPane() {}

    public MainFrameWestPane(JSplitPane mainPane) {
        super(mainPane);
        this.setPreferredSize(new Dimension(150, 600));
        this.setMinimumSize(new Dimension(100, 400));
    }
}
