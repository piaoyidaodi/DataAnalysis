package cc.comac.ui.menu;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultHelpMenu extends JMenu {

    public DefaultHelpMenu() {
        super();
    }
    
    public DefaultHelpMenu(JComponent parent){
      //Initial HelpMenu
        super("Help");
        
        addMenuItem(parent,this,"About");
    }

    void addMenuItem(JComponent parent,JMenu menu, String menuItemName,Action...actions) {
        JMenuItem menuItem=DefaultHelpMenuItems.creatMenuItem(parent,menuItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
}
