package cc.comac.ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultFileMenu extends JMenu {
    public DefaultFileMenu() {
        // Initial DefaultFileMenu
        super("File");

        // Add MenuItems
        addMenuItem(this, "Open File... ");
        addMenuItem(this, "Open Directory ... ");
    }

    void addMenuItem(JMenu menu, String meunItemName) {
        JMenuItem menuItem = DefaultFileMenuItems.creatMenuItem(meunItemName);
        menu.add(menuItem);
    }
}
