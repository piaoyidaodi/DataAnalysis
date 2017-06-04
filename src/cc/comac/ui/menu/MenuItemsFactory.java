package cc.comac.ui.menu;

import javax.swing.JMenuItem;

public class MenuItemsFactory {

    private static JMenuItem menuItem=null;
    
    public static JMenuItem getOpenDirectoryItem() {
        menuItem=new JMenuItem("Open Directory...");
        return menuItem;
    }

    public static JMenuItem getOpenFileItem() {
        menuItem=new JMenuItem("Open File...");
        return menuItem;
    }

}
