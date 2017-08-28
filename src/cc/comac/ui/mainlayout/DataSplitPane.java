package cc.comac.ui.mainlayout;

import javax.swing.JSplitPane;

public class DataSplitPane extends JSplitPane {

    public DataSplitPane() {
        super(JSplitPane.VERTICAL_SPLIT, false);
        this.setDividerLocation(500);
//        this.setDividerLocation((int)((this.getMaximumDividerLocation()-this.getMinimumDividerLocation()/4)));
        this.setDividerSize(5);
    }
}
