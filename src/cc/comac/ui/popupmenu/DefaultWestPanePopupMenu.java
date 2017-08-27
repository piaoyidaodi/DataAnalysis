package cc.comac.ui.popupmenu;


import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import cc.comac.ui.menu.MenuItemsFactory;

public class DefaultWestPanePopupMenu extends JPopupMenu {
    
    
    public DefaultWestPanePopupMenu(JComponent parent) {
        super();
        addMenuItem(parent,this);
    }

    private void addMenuItem(JComponent parent,JPopupMenu menu,Action...actions) {
        JMenuItem drawItem=MenuItemsFactory.getDrawItem(parent);
        JMenuItem openInExplorerItem=MenuItemsFactory.getOpenInExplorerItem(parent);
        
        this.add(drawItem);
        this.add(openInExplorerItem);
    }
}
