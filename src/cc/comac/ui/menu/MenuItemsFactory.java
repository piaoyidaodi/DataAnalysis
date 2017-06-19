package cc.comac.ui.menu;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import cc.comac.util.ActionFactory;

public class MenuItemsFactory {

    private static JMenuItem menuItem=null;

    public static JMenuItem getOpenFileItem() {
        menuItem=new JMenuItem(ActionFactory.getOpenFileAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt F"));
        return menuItem;
    }
    
    public static JMenuItem getOpenDirectoryItem() {
        menuItem=new JMenuItem(ActionFactory.getOpenDirectoryAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt D"));
        return menuItem;
    }

    public static JMenuItem getSaveItem() {
        menuItem=new JMenuItem(ActionFactory.getSaveAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        return menuItem;
    }

    public static JMenuItem getSaveAsItem() {
        menuItem=new JMenuItem(ActionFactory.getSaveAsAction());
        menuItem.setDisplayedMnemonicIndex(5);
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
        return menuItem;
    }

    public static JMenuItem getExitItem() {
        menuItem=new JMenuItem(ActionFactory.getExitAction());
        return menuItem;
    }

    public static JMenuItem getAboutItem() {
        menuItem=new JMenuItem(ActionFactory.getAboutAction());
        return menuItem;
    }

}
