package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrameMenuBar extends JMenuBar {
    
    public MainFrameMenuBar() {
        super();
    }
    
    public MainFrameMenuBar(JComponent parent,String...menuOptionName){
        super();
        if (menuOptionName.length>0){
            for(String menuName:menuOptionName){
                JMenu menu=MainFrameMenuOption.creatMenu(parent,menuName);
                this.add(menu);
            }
        }
    }
    
}
