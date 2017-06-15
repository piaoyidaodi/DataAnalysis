package cc.comac.ui.menu;

import javax.swing.JMenuItem;

public class DefaultFileMenuItems extends JMenuItem {

    public DefaultFileMenuItems() {
        super();
    }

    public DefaultFileMenuItems(String menuItemName) {
        creatMenuItem(menuItemName);
    }

    public static JMenuItem creatMenuItem(String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "Open File...":
        case "OpenFile":
            menuItem=MenuItemsFactory.getOpenFileItem();
            break;

        case "Open Directory...":
        case "OpenDirectory":
            menuItem=MenuItemsFactory.getOpenDirectoryItem();
            break;
           
        case "Save...":
        case "Save":
            menuItem=MenuItemsFactory.getSaveItem();
            break;
            
        case "SaveAs...":
        case "SaveAs":
            menuItem=MenuItemsFactory.getSaveAsItem();
            break;
            
        case "Exit":
            menuItem=MenuItemsFactory.getExitItem();
            break;
            
        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        return menuItem;
    }
    
}
