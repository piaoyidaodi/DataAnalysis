package cc.comac.ui.menu;

import javax.swing.JMenuItem;

import cc.comac.util.ActionFactory;

public class MenuItemsFactory {

    private static JMenuItem menuItem=null;
    
    public static JMenuItem getOpenDirectoryItem() {
        menuItem=new JMenuItem(ActionFactory.getOpenDirectoryAction());
        return menuItem;
    }

    public static JMenuItem getOpenFileItem() {
        menuItem=new JMenuItem(ActionFactory.getOpenFileAction());
        return menuItem;
    }

    public static JMenuItem getSaveItem() {
        menuItem=new JMenuItem(ActionFactory.getSaveAction());
        return menuItem;
    }

    public static JMenuItem getSaveAsItem() {
        menuItem=new JMenuItem(ActionFactory.getSaveAsAction());
        return menuItem;
    }

    public static JMenuItem getExitItem() {
        menuItem=new JMenuItem(ActionFactory.getExitAction());
        return menuItem;
    }

}
