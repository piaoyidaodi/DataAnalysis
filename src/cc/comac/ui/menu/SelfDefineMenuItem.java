package cc.comac.ui.menu;

import javax.swing.JMenuItem;

public class SelfDefineMenuItem extends JMenuItem {
    
    private JMenuItem menuItem=null; 
    
    public SelfDefineMenuItem() {
        super();
    }
    
    public SelfDefineMenuItem(String menuItemName){
        menuItem=new JMenuItem(menuItemName);
    }
}
