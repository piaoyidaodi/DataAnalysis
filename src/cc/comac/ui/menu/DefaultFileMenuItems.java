package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenuItem;

public class DefaultFileMenuItems extends JMenuItem {

    public DefaultFileMenuItems() {
        super();
    }

    public DefaultFileMenuItems(JComponent parent,String menuItemName) {
        creatMenuItem(parent,menuItemName);
    }

    public static JMenuItem creatMenuItem(JComponent parent,String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "Open File...":
        case "OpenFile":
            menuItem=MenuItemsFactory.getOpenFileItem(parent);
            break;

        case "Open Directory...":
        case "OpenDirectory":
            menuItem=MenuItemsFactory.getOpenDirectoryItem(parent);
            break;
           
        case "Save...":
        case "Save":
            menuItem=MenuItemsFactory.getSaveItem(parent);
            break;
            
        case "SaveAs...":
        case "SaveAs":
            menuItem=MenuItemsFactory.getSaveAsItem(parent);
            break;
            
        case "Exit":
            menuItem=MenuItemsFactory.getExitItem(parent);
            break;
            
        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        return menuItem;
    }
    
}
