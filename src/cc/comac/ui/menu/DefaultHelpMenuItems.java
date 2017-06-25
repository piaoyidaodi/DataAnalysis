package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenuItem;

public class DefaultHelpMenuItems extends JMenuItem {

    public DefaultHelpMenuItems() {
        super();
    }

    public static JMenuItem creatMenuItem(JComponent parent,String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "About":
            menuItem=MenuItemsFactory.getAboutItem(parent);
            break;

        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        
        return menuItem;
    }
    
    
    
}
