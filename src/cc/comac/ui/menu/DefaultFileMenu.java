package cc.comac.ui.menu;

import javax.swing.Action;
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

    void addMenuItem(JMenu menu, String meunItemName,Action...actions) {
        JMenuItem menuItem = DefaultFileMenuItems.creatMenuItem(meunItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
}
