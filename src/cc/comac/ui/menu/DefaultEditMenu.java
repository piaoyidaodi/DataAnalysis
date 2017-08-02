package cc.comac.ui.menu;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DefaultEditMenu extends JMenu {
    
    public DefaultEditMenu(){
        //Initial EditMenu
        super();
        
    }
    
    public DefaultEditMenu(JComponent parent){
        //Initial EditMenu
        super("Edit");
        
        addMenuItem(parent,this, "ThemeChooser");
        addMenuItem(parent, this, "WorkSpace");
    }

    void addMenuItem(JComponent parent,JMenu menu,String menuItemName,Action...actions) {
        JMenuItem menuItem=DefaultEditMenuItems.creatMenuItem(parent,menuItemName);
        if (actions.length==1) {
            menuItem.addActionListener(actions[0]);
        }
        menu.add(menuItem);
    }
    
}
