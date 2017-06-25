package cc.comac.ui.menu;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultFileMenu extends JMenu {
    
    public DefaultFileMenu() {
        super();
    }
    
    public DefaultFileMenu(JComponent parent) {
        // Initial DefaultFileMenu
        super("File");

        // Add MenuItems
        addMenuItem(parent,this, "Open File...");
        addMenuItem(parent,this, "Open Directory...");
        addMenuItem(parent,this, "Save...");
        addMenuItem(parent,this, "SaveAs...");
        addMenuItem(parent,this, "Exit");
    }

    void addMenuItem(JComponent parent,JMenu menu, String meunItemName,Action...actions) {
        JMenuItem menuItem = DefaultFileMenuItems.creatMenuItem(parent,meunItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
}
