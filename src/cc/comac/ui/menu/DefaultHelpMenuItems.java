package cc.comac.ui.menu;

import javax.swing.JMenuItem;

public class DefaultHelpMenuItems extends JMenuItem {

    public DefaultHelpMenuItems() {
        super();
    }

    public static JMenuItem creatMenuItem(String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "About":
            menuItem=MenuItemsFactory.getAboutItem();
            break;

        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        
        return menuItem;
    }
    
    
    
}
