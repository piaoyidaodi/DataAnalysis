package cc.comac.ui.toolbar;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import cc.comac.util.ActionFactory;

public class DefaultToolBar extends JToolBar {

    public DefaultToolBar() {
        super();
    }

    public DefaultToolBar(JFrame parent) {
        super();
        
        this.add(ActionFactory.getOpenFileAction(this));
        this.add(ActionFactory.getOpenDirectoryAction(this));
        this.add(ActionFactory.getSaveAction(this));
        
        this.addSeparator();

        
        this.addSeparator();
        this.add(ActionFactory.getAboutAction(this));
        
        this.setPreferredSize(new Dimension(400, 25));
        
    }

}
