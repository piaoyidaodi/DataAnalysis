package cc.comac.ui.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultFileMenu extends JMenu {
    public DefaultFileMenu() {
        // Initial DefaultFileMenu
        super("File");

        // Add MenuItems
        addMenuItem(this, "Open File...");
        addMenuItem(this, "Open Directory...");
        addMenuItem(this, "Save...");
        addMenuItem(this, "SaveAs...");
        addMenuItem(this, "Exit");
    }

    void addMenuItem(JMenu menu, String meunItemName,ActionListener...actionListeners) {
        JMenuItem menuItem = DefaultFileMenuItems.creatMenuItem(meunItemName);
        if (actionListeners.length==1) {
            menuItem.addActionListener(actionListeners[0]);
        }
        menu.add(menuItem);
    }
}
