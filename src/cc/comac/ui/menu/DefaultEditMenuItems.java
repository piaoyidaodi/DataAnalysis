package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenuItem;

public class DefaultEditMenuItems extends JMenuItem{
    
    public DefaultEditMenuItems() {
        super();
    }
    
    public static JMenuItem creatMenuItem(JComponent parent,String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "ThemeChooser":
            menuItem =MenuItemsFactory.getThemeItem(parent);
            break;
            
        case "WorkSpace":
            menuItem=MenuItemsFactory.getWorkSpaceItem(parent);
            break;

        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        return menuItem;
    }

}
