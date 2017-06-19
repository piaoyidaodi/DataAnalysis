package cc.comac.ui.menu;

import javax.swing.JMenuItem;

public class DefaultEditMenuItems extends JMenuItem{
    
    public DefaultEditMenuItems() {
        super();
    }
    
    public static JMenuItem creatMenuItem(String menuItemName) {
        JMenuItem menuItem=null;
        
        switch (menuItemName) {
        case "":
            
            break;

        default:
            menuItem=new JMenuItem(menuItemName);
            break;
        }
        return menuItem;
    }

}
