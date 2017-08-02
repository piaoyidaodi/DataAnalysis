package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import cc.comac.util.ActionFactory;

public class MenuItemsFactory {

    private static JMenuItem menuItem=null;

    public static JMenuItem getOpenFileItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getOpenFileAction(parent));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt F"));
        return menuItem;
    }
    
    public static JMenuItem getOpenDirectoryItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getOpenDirectoryAction(parent));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt D"));
        return menuItem;
    }

    public static JMenuItem getSaveItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getSaveAction(parent));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        return menuItem;
    }

    public static JMenuItem getSaveAsItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getSaveAsAction(parent));
        menuItem.setDisplayedMnemonicIndex(5);
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
        return menuItem;
    }

    public static JMenuItem getExitItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getExitAction(parent));
        return menuItem;
    }

    public static JMenuItem getAboutItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getAboutAction(parent));
        return menuItem;
    }

    public static JMenuItem getThemeItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getThemeAction(parent));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt T"));
        return menuItem;
    }

    public static JMenuItem getWorkSpaceItem(JComponent parent) {
        menuItem=new JMenuItem(ActionFactory.getWorkSpaceAction(parent));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt W"));
        return menuItem;
    }

}
