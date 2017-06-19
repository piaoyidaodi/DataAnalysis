package cc.comac.ui.menu;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultHelpMenu extends JMenu {

    public DefaultHelpMenu() {
        //Initial HelpMenu
        super("Help");
        
        addMenuItem(this,"About");
    }

    void addMenuItem(JMenu menu, String menuItemName,Action...actions) {
        JMenuItem menuItem=DefaultHelpMenuItems.creatMenuItem(menuItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
}
