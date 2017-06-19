package cc.comac.ui.menu;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultEditMenu extends JMenu {
    
    public DefaultEditMenu(){
        //Initial EditMenu
        super("Edit");
        
        addMenuItem(this, "TODO");
    }

    void addMenuItem(JMenu menu,String menuItemName,Action...actions) {
        JMenuItem menuItem=DefaultEditMenuItems.creatMenuItem(menuItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
    
}
