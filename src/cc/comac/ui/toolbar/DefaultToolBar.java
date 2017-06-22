package cc.comac.ui.toolbar;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import cc.comac.util.ActionFactory;

public class DefaultToolBar extends JToolBar {

    public DefaultToolBar() {
        super();
    }

    public DefaultToolBar(JFrame parent) {
        super();
        
        this.add(ActionFactory.getOpenFileAction());
        this.add(ActionFactory.getOpenDirectoryAction());
        this.add(ActionFactory.getSaveAction());
        
        this.addSeparator();

        
        this.addSeparator();
        this.add(ActionFactory.getAboutAction());
    }

}
