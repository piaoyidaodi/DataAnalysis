package cc.comac.ui.popupmenu;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import cc.comac.ui.menu.MenuItemsFactory;

public class DefaultCenterPanePopupMenu extends JPopupMenu {

    public DefaultCenterPanePopupMenu(JComponent parent) {
        super();
        addMenuItem(parent,this);
    }

    private void addMenuItem(JComponent parent, JPopupMenu menu,Action...actions) {
        JMenuItem closeTabItem=MenuItemsFactory.getCloseTabItem(parent);
        
        this.add(closeTabItem);
    }
}
